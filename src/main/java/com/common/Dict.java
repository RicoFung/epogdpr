package com.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import chok.common.BaseModel;
import chok.devwork.springboot.BaseService;

public class Dict 
{
	public static Map<String, String> countryMap = new HashMap<String, String>()
	{
		private static final long serialVersionUID = -6230843137646934856L;

		{
			put("英国", "en_GB");
			put("法国", "fr_FR");
		}
	};
	
	/**
	 * 获取集合(通用)
	 * @param params
	 * @param service
	 * @return List<Object>
	 */
	private static List<Object> getList(Map<String, Object> params, BaseService service)
	{
		List<Object> list = new ArrayList<Object>();
		List<BaseModel> resultData = service.query(params);
		for(BaseModel o : resultData)
		{
			list.add(o.getM());
		}
		return list;
	}
	/**
	 * 获取JSON集合(通用)
	 * @param params
	 * @param service
	 * @return List<Object>
	 */
	private static List<Object> getJsons(Map<String, Object> params, BaseService service)
	{
		List<Object> list = new ArrayList<Object>();
		List<BaseModel> resultData = service.query(params);
		for(BaseModel o : resultData)
		{
			list.add(JSON.toJSON(o.getM()));
		}
		return list;
	}
}
