package com.epo.client.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epo.client.entity.VipPolicyFeedback;
import com.epo.client.service.VipPolicyFeedbackService;

import chok.devwork.BaseController;
import chok.util.TimeUtil;

@Scope("prototype")
@Controller(value="ClientVipPolicyFeedbackAction")
@RequestMapping("/client/vippolicyfeedback")
public class VipPolicyFeedbackAction extends BaseController<VipPolicyFeedback>
{
	@Autowired
	private VipPolicyFeedbackService service;
	
	@RequestMapping("/feedback")
	public void feedback() 
	{
		try
		{
			VipPolicyFeedback po = new VipPolicyFeedback();
			po.setClientIp(req.getString("clientIp"));
			po.setClientSentTime(req.getString("clientSendTime"));
			po.setBrowserAgent(req.getString("browserAgent"));
			po.setFeedbackResult(req.getString("feedbackResult"));
			po.setFeedbackTime(TimeUtil.getCurrentTime());
			po.setMemberCode(req.getString("memberCode"));
			result = service.feedback(po);
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		printJson(result);
	}
	

	@RequestMapping("/get")
	public String get() 
	{
		put("memberCode", req.getString("memberCode"));
		String lang = req.getString("lang");
		lang = lang.substring(0, 1).toUpperCase() + lang.substring(1);
		return "jsp/client/vippolicyfeedback/get"+lang;
	}

}
