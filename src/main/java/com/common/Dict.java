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
	
	public static String DATE_EXCEL_FORMAT = PropertiesUtil.getValue("config/", "date.excel.format");
	public static String DATE_APP_FORMAT = PropertiesUtil.getValue("config/", "date.app.format");
	public static String MAIL_DELIVER = PropertiesUtil.getValue("config/", "mail.deliver");
	public static String MAIL_TEMPLATE_PREFIX = PropertiesUtil.getValue("config/", "mail.template.prefix");
	public static String MAIL_PRIVACY_POLICY_URL = PropertiesUtil.getValue("config/", "mail.privacy.policy.url");
	public static String MAIL_PRIVACY_POLICY_KEY = PropertiesUtil.getValue("config/", "mail.privacy.policy.key");
	public static String MAIL_BOTTOM_LOGO_URL = PropertiesUtil.getValue("config/", "mail.bottom_logo.url");
	
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
