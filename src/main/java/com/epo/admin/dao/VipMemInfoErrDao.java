package com.epo.admin.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.epo.admin.entity.VipMemInfoErr;

import chok.devwork.springboot.BaseDao;

@Repository
public class VipMemInfoErrDao extends BaseDao<VipMemInfoErr,Long>
{
	@Resource//(name = "firstSqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	protected SqlSession getSqlSession()
	{
		return sqlSession;
	}
	
	@Override
	public Class<VipMemInfoErr> getEntityClass()
	{
		return VipMemInfoErr.class;
	}
}
