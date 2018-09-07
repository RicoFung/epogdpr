package com.epo.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.epo.admin.entity.VipMemInfo;
import com.epo.admin.entity.VipMemInfoErr;
import com.epo.admin.service.VipMemInfoService;

import chok.devwork.BaseController;

@Scope("prototype")
@Controller
@RequestMapping("/admin/vipmeminfo")
public class VipMemInfoAction extends BaseController<VipMemInfo>
{
	static Logger log = LoggerFactory.getLogger(VipMemInfoAction.class);
	
	@Autowired
	private VipMemInfoService service;

	@RequestMapping("/imp")
	public String imp()
	{
		put("queryParams", req.getParameterValueMap(false, true));
		return "jsp/admin/vipmeminfo/imp";
	}

	@SuppressWarnings({ "unchecked", "serial" })
	@RequestMapping("/imp2")
	public void imp2(@RequestParam("myFile") CommonsMultipartFile files[])
	{
		try
		{
			Map<String, Object> resultMap = service.imp2(files);
			List<VipMemInfo> sucRows = ((List<VipMemInfo>)resultMap.get("sucRows"));
			List<VipMemInfo> errRows = ((List<VipMemInfo>)resultMap.get("errRows"));
			if (sucRows.size() > 0)
				result.setSuccess(true);
			else
				result.setSuccess(false);
				
			result.setMsg("(导入成功: "+sucRows.size()+")");
			result.setMsg(result.getMsg()+" (导入失败: "+errRows.size()+")");
			
			if (errRows.size() > 0)
			{
				result.setData(new HashMap<Object, Object>()
				{{
					put("rowid", resultMap.get("rowid"));
				}});
				log.info("----------------------------");
				for(VipMemInfo po: errRows) log.info(po.toString());
				log.info("----------------------------");
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage());
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		printJson(result);
	}

	@RequestMapping("/sendEmail")
	public void sendEmail()
	{
		log.info("==> (sendEmail) jsonparams: " + req.getString("jsonparams"));
		List<VipMemInfo> vipMemInfos = JSON.parseArray(req.getString("jsonparams"), VipMemInfo.class);
		result = service.sendEmail(vipMemInfos);
		printJson(result);
	}
	
	@RequestMapping("/get")
	public String get()
	{
		put("po", service.get(req.getLong("id")));
		put("queryParams", req.getParameterValueMap(false, true));
		return "jsp/admin/vipmeminfo/get";
	}

	@RequestMapping("/query")
	public String query()
	{
		put("queryParams", req.getParameterValueMap(false, true));
		return "jsp/admin/vipmeminfo/query";
	}

	@RequestMapping("/query2")
	public void query2()
	{
		Map<String, Object> m = req.getParameterValueMap(false, true);
		result.put("total", service.getCount(m));
		result.put("rows", service.query(req.getDynamicSortParameterValueMap(m)));
		printJson(result.getData());
	}

	@RequestMapping("/exp")
	public void exp()
	{
		Map<String, Object> m = req.getParameterValueMap(false, true);
		List<VipMemInfo> list = service.query(m);
		exp(list, "xlsx");
	}
	
	@RequestMapping("/expErr")
	public void expErr()
	{
		Map<String, Object> m = req.getParameterValueMap(false, true);
		List<VipMemInfoErr> list = service.queryErr(m);
		String headerNames = "memberCode,email,joinDate,storeCode,country,msg";
		String dataColumns = "memberCode,email,joinDate,storeCode,countryCn,msg";
		exp(list, 
			"error_rows", 
			null, 
			headerNames,
			dataColumns,
			"xlsx");
	}

	@RequestMapping("/del")
	public void del() 
	{
		try
		{
			service.del(req.getStringArray("memberCode[]"));
			result.setSuccess(true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		printJson(result);
	}
}
