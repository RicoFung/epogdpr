package com.epo.admin.service;

import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.common.Dict;
//import com.common.MailUtil;
import com.epo.admin.dao.VipMemInfoDao;
import com.epo.admin.dao.VipMemInfoErrDao;
import com.epo.admin.dao.VipPolicyFeedbackDao;
import com.epo.admin.entity.VipMemInfo;
import com.epo.admin.entity.VipMemInfoErr;

import chok.devwork.Result;
import chok.devwork.springboot.BaseDao;
import chok.devwork.springboot.BaseService;
import chok.util.EncryptionUtil;
import chok.util.MailUtil;
import chok.util.POIUtil;
import chok.util.TimeUtil;
import chok.util.UniqueId;
import chok.util.ValidationUtil;

@Service
public class VipMemInfoService extends BaseService<VipMemInfo, String>
{
	static Logger log = LoggerFactory.getLogger(VipMemInfoService.class);

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	MessageSource				source;
	@Autowired
	private VipMemInfoDao		vipMemInfoDao;
	@Autowired
	private VipMemInfoErrDao	vipMemInfoErrDao;
	@Autowired
	VipPolicyFeedbackDao		vipPolicyFeedbackDao;

	@Override
	public BaseDao<VipMemInfo, String> getEntityDao()
	{
		return vipMemInfoDao;
	}

	/**
	 * 上传
	 * 
	 * @param files
	 * @return Map<String, Object> {"sucRows":List<VipMemInfo>,
	 *         "errRows":List<VipMemInfoErr>, "rowid":rowid}
	 * @throws Exception
	 */
	public Map<String, Object> imp2(CommonsMultipartFile files[]) throws Exception
	{
		List<String[]> list = POIUtil.readExcel(files[0]);
		// 校验数据
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, List<VipMemInfo>> resultRows = validateRows(list);
		// sucRows
		List<VipMemInfo> sucRows = resultRows.get("sucRows");
		resultMap.put("sucRows", sucRows);
		if (sucRows.size() > 0)
		{
			for (int j = 0; j < sucRows.size(); j++)
			{
				VipMemInfo po = sucRows.get(j);
				po.setJoinDate(TimeUtil.toggleFormat(po.getJoinDate(), Dict.DATE_EXCEL_FORMAT, Locale.ENGLISH, Dict.DATE_APP_FORMAT, Locale.CHINA));
				vipMemInfoDao.add(po);
			}
		}
		// errRows
		List<VipMemInfo> errRows = resultRows.get("errRows");
		resultMap.put("errRows", errRows);
		if (errRows.size() > 0)
		{
			long rowid = UniqueId.genId();
			for (int j = 0; j < errRows.size(); j++)
			{
				VipMemInfoErr po = new VipMemInfoErr();
				po.setMemberCode(errRows.get(j).getMemberCode());
				po.setEmail(errRows.get(j).getEmail());
				po.setJoinDate(errRows.get(j).getJoinDate());
				po.setStoreCode(errRows.get(j).getStoreCode());
				po.setCountry(errRows.get(j).getCountry());
				po.setMsg(errRows.get(j).getMsg());
				po.setPassed(errRows.get(j).getPassed());
				po.setRowid(rowid);
				vipMemInfoErrDao.add(po);
			}
			resultMap.put("rowid", rowid);
		}
		return resultMap;
	}

	/**
	 * 获取上传时，不合法数据
	 * 
	 * @param m
	 * @return
	 */
	public List<VipMemInfoErr> queryErr(Map<String, Object> m)
	{
		List<VipMemInfoErr> list = vipMemInfoErrDao.query(m);
		// vipMemInfoErrDao.del(Long.valueOf(m.get("rowid").toString())); //
		// 用户读取失败数据后立即删除
		return list;
	}

	/**
	 * 发送邮件
	 * 
	 * @param sendEmail
	 * @throws Exception
	 */
	public Result sendEmail(List<VipMemInfo> vipMemInfos) // throws Exception
	{
		Result r = new Result();
		// 按country分组
		Map<String, List<VipMemInfo>> vipMemInfoGroup = vipMemInfos.stream().collect(Collectors.groupingBy(VipMemInfo::getCountry));
		// 按country分组选择template并发送邮件
		for (Entry<String, List<VipMemInfo>> entry : vipMemInfoGroup.entrySet())
		{
			String lang = entry.getKey();
			Locale locale = new Locale(lang.split("_")[0], lang.split("_")[1]);
			List<VipMemInfo> list = entry.getValue();
			// 设置邮件信息-发送者
			String deliver = Dict.MAIL_DELIVER;
			// 设置邮件信息-多语言主题
			String subject = source.getMessage("mail.subject", null, locale);
			// 设置邮件信息-多语言模板
			String template = Dict.MAIL_TEMPLATE_PREFIX + "_" + lang.toLowerCase();
			// 遍历 分组后的 List<VipMemInfo> 发送邮件
			list.forEach(item ->
			{
				// 验证重复发送邮件
				if (item.getSendStatus().equals("1"))
				{
					r.setSuccess(false);
					r.setMsg("(" + item.getMemberCode() + ")邮件已发送, 不能重复发送！");
					return;
				}
				// 设置邮件信息-接收者
				String[] receiver = new String[] { item.getEmail() };
				// 设置邮件信息-抄送者
				String[] carbonCopy = null;
				// 设置邮件信息-模板上下文（用于模板变量赋值）
				Context context = new Context();
				// 1. 生成token
				String clientToken = null;
				try
				{
					clientToken = EncryptionUtil.encodeAES(item.getMemberCode(), Dict.MAIL_PRIVACY_POLICY_KEY);
					// 需 URL 转码
					log.info("(clientToken) URLEncoder before:" + clientToken);
					clientToken = URLEncoder.encode(clientToken, "UTF-8");
					log.info("(clientToken) URLEncoder after:" + clientToken);
				}
				catch (Exception e)
				{
					log.error(e.getMessage());
					e.printStackTrace();
					r.setSuccess(false);
					r.setMsg(e.getMessage());
				}
				// 生成token失败，不往下执行
				if (!r.isSuccess())
					return;
				// 2. 设置邮件中 privacy policy 超链接
				context.setVariable("privacy_policy_url", Dict.MAIL_PRIVACY_POLICY_URL + "clientToken=" + clientToken + "&" + "lang=" + lang);
				context.setVariable("bottom_logo_url", Dict.MAIL_BOTTOM_LOGO_URL);
				// 3. 发送邮件
				String sendStatus = "0";
				try
				{
					MailUtil.sendTemplateEmail(mailSender, templateEngine, deliver, receiver, carbonCopy, subject, template, context);
					sendStatus = "1";
					r.setSuccess(true);
					r.setMsg("发送成功！");
				}
				catch (Exception e)
				{
					sendStatus = "-1";
					log.error(e.getMessage());
					e.printStackTrace();
					r.setSuccess(false);
					r.setMsg(e.getMessage());
				}
				finally
				{
					item.setSendTime(TimeUtil.getCurrentTime());
					vipMemInfoDao.updSendStatusByMemberCode(sendStatus, item.getMemberCode());
				}
			});
		}
		return r;
	}

	/**
	 * 校验Row
	 * 
	 * @param rows
	 * @return Map<String, List<VipMemInfo>>
	 * @throws Exception
	 */
	private Map<String, List<VipMemInfo>> validateRows(List<String[]> rows) throws Exception
	{
		List<VipMemInfo> sucRows = new LinkedList<VipMemInfo>();
		List<VipMemInfo> errRows = new LinkedList<VipMemInfo>();

		for (int j = 0; j < rows.size(); j++)
		{
			String[] r = rows.get(j);
			VipMemInfo po = new VipMemInfo();
			po.setMemberCode(r[0]);
			po.setEmail(r[1]);
			po.setJoinDate(r[2]);
			po.setStoreCode(r[3]);
			po.setCountry(Dict.countryMap.get(r[4]));
			if (validateCell(po))
				sucRows.add(po);
			else
				errRows.add(po);
		}

		Map<String, List<VipMemInfo>> result = new HashMap<String, List<VipMemInfo>>()
		{
			private static final long serialVersionUID = 1L;
			{
				put("sucRows", sucRows);
				put("errRows", errRows);
			}
		};
		return result;
	}

	/**
	 * 校验Cell
	 * 
	 * @param po
	 * @return boolean
	 */
	private boolean validateCell(VipMemInfo po)
	{
		if (StringUtils.isBlank(po.getMemberCode()))
		{
			po.setPassed(false);
			po.setMsg(po.getMsg() + "(MemberCode can not be empty)");
		}
		else
		{
			int count = vipMemInfoDao.getCount(new HashMap<String, Object>()
			{
				private static final long serialVersionUID = 1L;
				{
					put("memberCode2", po.getMemberCode());
				}
			});
			if (count > 0)
			{
				po.setPassed(false);
				po.setMsg(po.getMsg() + "(MemberCode already exists)");
			}
		}

		if (StringUtils.isBlank(po.getEmail()))
		{
			po.setPassed(false);
			po.setMsg(po.getMsg() + "(Email can not be empty)");
		}
		else
		{
			if (!ValidationUtil.checkEmailFormat(po.getEmail()))
			{
				po.setPassed(false);
				po.setMsg(po.getMsg() + "(Email format is illegal)");
			}
			int count = vipMemInfoDao.getCount(new HashMap<String, Object>()
			{
				private static final long serialVersionUID = 1L;
				{
					put("email2", po.getEmail());
				}
			});
			if (count > 0)
			{
				po.setPassed(false);
				po.setMsg(po.getMsg() + "(Email already exists)");
			}
		}

		if (StringUtils.isBlank(po.getJoinDate()))
		{
			po.setPassed(false);
			po.setMsg(po.getMsg() + "(JoinDate can not be empty)");
		}
		else
		{
			try
			{
				TimeUtil.toggleFormat(po.getJoinDate(), Dict.DATE_EXCEL_FORMAT, Locale.ENGLISH, Dict.DATE_APP_FORMAT, Locale.CHINA);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
				po.setPassed(false);
				po.setMsg(po.getMsg() + "(JoinDate format is illegal)");
			}
		}

		if (StringUtils.isBlank(po.getStoreCode()))
		{
			po.setPassed(false);
			po.setMsg(po.getMsg() + "(StoreCode can not be empty)");
		}

		if (StringUtils.isBlank(po.getCountry()))
		{
			po.setPassed(false);
			po.setMsg(po.getMsg() + "(Country can not be empty)");
		}
		return po.getPassed();
	}

	/**
	 * 删除
	 * 
	 * @param memberCodes
	 */
	public void del(String[] memberCodes)
	{
		for (String memberCode : memberCodes)
		{
			vipPolicyFeedbackDao.del(memberCode);
			vipMemInfoDao.del(memberCode);
		}
	}
}
