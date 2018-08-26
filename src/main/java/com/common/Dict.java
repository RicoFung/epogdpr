package com.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epo.admin.dao.VipMemInfoDao;

@SuppressWarnings("serial")
@Component
public class Dict 
{
	@Autowired
	private VipMemInfoDao dao;
	
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
