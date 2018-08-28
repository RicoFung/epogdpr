package com.epo.client.dao;

import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import chok.devwork.springboot.BaseDao;
import com.epo.client.entity.VipPolicyFeedback;

@Repository(value="ClientVipPolicyFeedbackDao")
public class VipPolicyFeedbackDao extends BaseDao<VipPolicyFeedback,Long>
{
	@Resource//(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	protected SqlSession getSqlSession()
	{
		return sqlSession;
	}
	
	@Override
	public Class<VipPolicyFeedback> getEntityClass()
	{
		return VipPolicyFeedback.class;
	}
}
