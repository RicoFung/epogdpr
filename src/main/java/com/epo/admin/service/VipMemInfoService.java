package com.epo.admin.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.thymeleaf.context.Context;

import com.common.Dict;
import com.common.MailUtil;
import com.epo.admin.dao.VipMemInfoDao;
import com.epo.admin.dao.VipMemInfoErrDao;
import com.epo.admin.entity.VipMemInfo;
import com.epo.admin.entity.VipMemInfoErr;

import chok.devwork.springboot.BaseDao;
import chok.devwork.springboot.BaseService;
import chok.util.POIUtil;
import chok.util.UniqueId;
import chok.util.ValidationUtil;

@Service
public class VipMemInfoService extends BaseService<VipMemInfo, Long>
{
	static Logger log = LoggerFactory.getLogger(VipMemInfoService.class);
	
	@Autowired
	private VipMemInfoDao vipMemInfoDao;
	@Autowired
	private VipMemInfoErrDao vipMemInfoErrDao;

	@Override
	public BaseDao<VipMemInfo, Long> getEntityDao()
	{
		return vipMemInfoDao;
	}
	
	/**
	 * 上传
	 * @param files
	 * @return Map<String, Object> {"sucRows":List<VipMemInfo>, "errRows":List<VipMemInfoErr>, "rowid":rowid}
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
	 * @param m
	 * @return
	 */
	public List<VipMemInfoErr> queryErr(Map<String, Object> m)
	{
		return vipMemInfoErrDao.query(m);
	}
	
	/**
	 * 发送邮件
	 * @param sendEmail
	 * @throws Exception 
	 */
	public void sendEmail(List<VipMemInfo> vipMemInfos) throws Exception
	{
		// 按country分组
		Map<String, List<VipMemInfo>> vipMemInfoGroup =vipMemInfos.stream().collect(Collectors.groupingBy(VipMemInfo::getCountry));
		// 按country分组选择template并发送邮件
		for (Entry<String, List<VipMemInfo>> entry : vipMemInfoGroup.entrySet()) 
		{
			List<VipMemInfo> list = entry.getValue();
		    // 设置邮件信息-发送者
			String deliver = Dict.SPRING_MAIL_USERNAME;
			// 设置邮件信息-主题
			String subject = Dict.MAIL_SUBJECT;
			// 设置邮件信息-语言模板
			String lang = entry.getKey();
			String template = Dict.MAIL_TEMPLATE+"_" + lang;
			// 遍历 分组后的 List<VipMemInfo> 发送邮件
			list.forEach(item->{
				// 设置邮件信息-接收者
				String[] receiver = new String[] {item.getEmail()};
				// 设置邮件信息-抄送者
				String[] carbonCopy = null;
				// 设置邮件信息-模板上下文（用于模板变量赋值）
				Context context = new Context();
				context.setVariable("privacy_policy_url", Dict.MAIL_PRIVACY_POLICY_URL+"memberCode="+item.getMemberCode()+"&lang="+lang);
				// 发送邮件
				try
				{
					MailUtil.sendTemplateEmail(deliver, receiver, carbonCopy, subject, template, context);
				}
				catch (MessagingException e)
				{
					log.error(e.getMessage());
					e.printStackTrace();
				}
			});
		}
	}

	/**
	 * 校验Row
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
			po.setStoreCode(r[4]);
			po.setCountry(Dict.countryMap.get(r[5]));
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
			if (!ValidationUtil.checkDateFormat(po.getJoinDate()))
			{
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
	
}
