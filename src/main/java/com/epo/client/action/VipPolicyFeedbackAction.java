package com.epo.client.action;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.Dict;
import com.epo.client.entity.VipPolicyFeedback;
import com.epo.client.service.VipPolicyFeedbackService;

import chok.devwork.BaseController;
import chok.util.EncryptionUtil;
import chok.util.TimeUtil;

@Scope("prototype")
@Controller(value="ClientVipPolicyFeedbackAction")
@RequestMapping("/client/vippolicyfeedback")
public class VipPolicyFeedbackAction extends BaseController<VipPolicyFeedback>
{
	private static Logger log = LoggerFactory.getLogger(VipPolicyFeedbackAction.class);
	
	@Autowired
	MessageSource source;
	@Autowired
	private VipPolicyFeedbackService service;
	
	@RequestMapping("/feedback")
	public void feedback() 
	{
		try
		{
			String lang = req.getString("lang");
			Locale locale = new Locale(lang.split("_")[0], lang.split("_")[1]);
			VipPolicyFeedback po = new VipPolicyFeedback();
			po.setClientIp(req.getString("clientIp"));
			po.setClientSentTime(req.getString("clientSendTime"));
			po.setBrowserAgent(req.getString("browserAgent"));
			po.setFeedbackResult(req.getString("feedbackResult"));
			po.setFeedbackTime(TimeUtil.getCurrentTime());
			
			String clientToken = req.getString("clientToken");
			log.info("==> clientToken: " + clientToken);
//			此处不需URL解码
//			log.info("(clientToken) URLDecoder before:"+clientToken);
//			clientToken = URLDecoder.decode(clientToken, "UTF-8");
//			log.info("(clientToken) URLDecoder before:"+clientToken);
			String memberCode = EncryptionUtil.decodeAES(clientToken, Dict.MAIL_PRIVACY_POLICY_KEY);
			log.info("==> clientToken 2 memberCode: " + memberCode);
			
			if (StringUtils.isBlank(memberCode)) 
			{
				String msg = source.getMessage("clientToken.invalid", null, locale) + " (" + clientToken + ")";
				log.error(msg);
				result.setMsg(msg);
				result.setSuccess(false);
			} 
			else
			{
				po.setMemberCode(memberCode);
				result = service.feedback(po, req.getString("lang"));
			}
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
		String lang = req.getString("lang");
		Locale locale = new Locale(lang.split("_")[0], lang.split("_")[1]);
		put("clientToken", req.getString("clientToken"));
		put("i18nAccept", source.getMessage("accept", null, locale));
		put("i18nReject", source.getMessage("reject", null, locale));
		put("i18nSubmit", source.getMessage("submit", null, locale));
		put("i18nAttention", source.getMessage("attention", null, locale));
		put("i18nRemind", source.getMessage("remind", null, locale));
		put("jspaction", req.getServletPath());
		String suffix = lang.split("_")[0].substring(0, 1).toUpperCase() + lang.substring(1).split("_")[0] + lang.substring(1).split("_")[1];
		return "jsp/client/vippolicyfeedback/get"+suffix;
	}

	public static void main(String[] args)
	{
		 System.out.println(EncryptionUtil.decodeAES("E+2u5B+IcrS7mrYXpZQPRA==", Dict.MAIL_PRIVACY_POLICY_KEY));
	}
}
