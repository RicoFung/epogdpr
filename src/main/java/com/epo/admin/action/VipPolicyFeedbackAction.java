package com.epo.admin.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import chok.devwork.BaseController;
import chok.util.CollectionUtil;
import com.epo.admin.service.VipPolicyFeedbackService;
import com.epo.admin.entity.VipPolicyFeedback;

@Scope("prototype")
@Controller
@RequestMapping("/admin/vippolicyfeedback")
public class VipPolicyFeedbackAction extends BaseController<VipPolicyFeedback>
{
	@Autowired
	private VipPolicyFeedbackService service;
	
	@RequestMapping("/add")
	public String add() 
	{
		put("queryParams",req.getParameterValueMap(false, true));
		return "jsp/admin/vippolicyfeedback/add";
	}
	@RequestMapping("/add2")
	public void add2(VipPolicyFeedback po) 
	{
		try
		{
			service.add(po);
			print("1");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			print("0:" + e.getMessage());
		}
	}
	
	@RequestMapping("/del")
	public void del() 
	{
		try
		{
			service.del(CollectionUtil.toLongArray(req.getLongArray("id[]", 0l)));
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
	
	@RequestMapping("/upd")
	public String upd() 
	{
		put("po", service.get(req.getLong("id")));
		put("queryParams",req.getParameterValueMap(false, true));
		return "jsp/admin/vippolicyfeedback/upd";
	}
	@RequestMapping("/upd2")
	public void upd2(VipPolicyFeedback po) 
	{
		try
		{
			service.upd(po);
			print("1");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			print("0:" + e.getMessage());
		}
	}

	@RequestMapping("/get")
	public String get() 
	{
		put("po",service.get(req.getLong("id")));
		put("queryParams",req.getParameterValueMap(false, true));
		return "jsp/admin/vippolicyfeedback/get";
	}

	@RequestMapping("/query")
	public String query() 
	{
		put("queryParams",req.getParameterValueMap(false, true));
		return "jsp/admin/vippolicyfeedback/query";
	}
	
	@RequestMapping("/query2")
	public void query2()
	{
		Map<String, Object> m = req.getParameterValueMap(false, true);
		result.put("total",service.getCount(m));
		result.put("rows",service.query(req.getDynamicSortParameterValueMap(m)));
		printJson(result.getData());
	}
	
	@RequestMapping("/exp")
	public void exp()
	{
		Map<String, Object> m = req.getParameterValueMap(false, true);
		List<VipPolicyFeedback> list = service.query(m);
		exp(list, "xlsx");
	}
}
