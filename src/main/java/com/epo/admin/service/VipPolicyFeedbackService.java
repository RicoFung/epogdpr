package com.epo.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import chok.devwork.springboot.BaseDao;
import chok.devwork.springboot.BaseService;
import com.epo.admin.dao.VipPolicyFeedbackDao;
import com.epo.admin.entity.VipPolicyFeedback;

@Service
public class VipPolicyFeedbackService extends BaseService<VipPolicyFeedback, String>
{
	@Autowired
	private VipPolicyFeedbackDao dao;

	@Override
	public BaseDao<VipPolicyFeedback, String> getEntityDao() 
	{
		return dao;
	}
}
