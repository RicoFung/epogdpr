package com.epo.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import chok.devwork.springboot.BaseDao;
import chok.devwork.springboot.BaseService;
import com.epo.client.dao.VipPolicyFeedbackDao;
import com.epo.client.entity.VipPolicyFeedback;

@Service(value="ClientVipPolicyFeedbackService")
public class VipPolicyFeedbackService extends BaseService<VipPolicyFeedback,Long>
{
	@Autowired
	private VipPolicyFeedbackDao dao;

	@Override
	public BaseDao<VipPolicyFeedback,Long> getEntityDao() 
	{
		return dao;
	}
	
	public void feedback(VipPolicyFeedback po)
	{
		
	}
}
