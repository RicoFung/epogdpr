package com.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epo.admin.dao.VipMemInfoDao;

import chok.util.PropertiesUtil;

@SuppressWarnings("serial")
@Component
public class Dict 
{
	@Autowired
	private VipMemInfoDao dao;
	
	// SPRING_MAIL_USERNAME
	public static String SPRING_MAIL_USERNAME = PropertiesUtil.getValue("config/", "spring.mail.username");
	public static String MAIL_SUBJECT = PropertiesUtil.getValue("config/", "mail.subject");
	public static String MAIL_TEMPLATE = PropertiesUtil.getValue("config/", "mail.template");
	public static String MAIL_PRIVACY_POLICY_URL = PropertiesUtil.getValue("config/", "mail.privacy.policy.url");
	
	// COUNTRY_MAP
	public static Map<String, String> countryMap = new HashMap<String, String>()
	{
		{
			put("英国", "en_GB");
			put("法国", "fr_FR");
		}
	};
	
	public List<Map<String, String>> getCountrys()
	{
		return dao.getCountrys();
	}
	
}
