package com.epo.admin.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.common.Dict;
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
	@Autowired
	private VipMemInfoDao dao;
	@Autowired
	private VipMemInfoErrDao dao2;

	@Override
	public BaseDao<VipMemInfo, Long> getEntityDao()
	{
		return dao;
	}
	
	public Map<String, Object> imp2(CommonsMultipartFile files[]) throws Exception
	{
		List<String[]> list = POIUtil.readExcel(files[0]);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, List<VipMemInfo>> resultRows = validateRows(list);
		
		List<VipMemInfo> sucRows = resultRows.get("sucRows");
		resultMap.put("sucRows", sucRows);
		List<VipMemInfo> errRows = resultRows.get("errRows");
		resultMap.put("errRows", errRows);
		
		if (sucRows.size() > 0)
		{
			for (int j = 0; j < sucRows.size(); j++)
			{
				VipMemInfo po = sucRows.get(j);
				dao.add(po);
			}
		}
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
				dao2.add(po);
			}
			resultMap.put("rowid", rowid);
		}
		return resultMap;
	}
	
	public List<VipMemInfoErr> queryErr(Map<String, Object> m)
	{
		return dao2.query(m);
	}
	
//	public void email(String[] emails)
//	{
//		for(String email: emails)
//		{
//			System.out.println(email);
//		}
//	}
	@Autowired
	private JavaMailSenderImpl mailSender;

	@Test
	public void email(String[] emails) throws Exception
	{
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		// 设置收件人，寄件人
		simpleMailMessage.setTo(new String[] { "olefun@icloud.com" });
		simpleMailMessage.setFrom("156812113@qq.com");
		simpleMailMessage.setSubject("Spring Boot Mail 邮件测试【文本】");
		simpleMailMessage.setText("这里是一段简单文本。");
		// 发送邮件
		mailSender.send(simpleMailMessage);

		System.out.println("邮件已发送");
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
			int count = dao.getCount(new HashMap<String, Object>()
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
			int count = dao.getCount(new HashMap<String, Object>()
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
	
	
	public static void main(String[] args)
	{
		
	}
}
