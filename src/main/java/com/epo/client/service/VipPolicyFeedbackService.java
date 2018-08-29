package com.epo.client.service;

import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.epo.client.dao.VipMemInfoDao;
import com.epo.client.dao.VipPolicyFeedbackDao;
import com.epo.client.entity.VipPolicyFeedback;

import chok.devwork.Result;
import chok.devwork.springboot.BaseDao;
import chok.devwork.springboot.BaseService;

@Service(value="ClientVipPolicyFeedbackService")
public class VipPolicyFeedbackService extends BaseService<VipPolicyFeedback,Long>
{
	static Logger log = LoggerFactory.getLogger(VipPolicyFeedbackService.class);
	
	@Autowired
	MessageSource source;
	@Autowired
	private VipPolicyFeedbackDao vipPolicyFeedbackDao;
	@Autowired
	private VipMemInfoDao vipMemInfoDao;

	@Override
	public BaseDao<VipPolicyFeedback,Long> getEntityDao() 
	{
		return vipPolicyFeedbackDao;
	}
	
	public Result feedback(VipPolicyFeedback po, String lang) throws Exception
	{
		Result r = check(po.getMemberCode(), lang);
		if (r.isSuccess()) vipPolicyFeedbackDao.add(po);
		return r;
	}
	
	@SuppressWarnings("serial")
	private Result check(String memberCode, String lang)
	{
		Locale locale = new Locale(lang.split("_")[0], lang.split("_")[1]);
		Result r = new Result();
		r.setMsg("Success !");
		int count = vipMemInfoDao.getCount(new HashMap<String, Object>()
		{{
			put("memberCode", memberCode);
		}});
		if (count < 1) 
		{
			r.setSuccess(false);
			r.setMsg("(" + memberCode + ") " + source.getMessage("memberCode.not.exis", null, locale));
			log.warn("(" + memberCode + ") " + source.getMessage("memberCode.not.exis", null, locale));
		}
		
		count = vipPolicyFeedbackDao.getCount(new HashMap<String, Object>()
		{{
			put("memberCode", memberCode);
		}});
		if (count > 0) 
		{
			r.setSuccess(false);
			r.setMsg("(" + memberCode + ") " + source.getMessage("memberCode.has.feedbacked", null, locale));
			log.warn("(" + memberCode + ") " + source.getMessage("memberCode.has.feedbacked", null, locale));
		}
		return r;
	}
}
