package com.epo.admin.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epo.admin.entity.VipPolicyFeedback;
import com.epo.admin.service.VipPolicyFeedbackService;

import chok.devwork.BaseController;

@Scope("prototype")
@Controller
@RequestMapping("/admin/vippolicyfeedback")
public class VipPolicyFeedbackAction extends BaseController<VipPolicyFeedback>
{
	@Autowired
	private VipPolicyFeedbackService service;
	
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
