package com.epo.client.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.epo.admin.entity.VipMemInfo;

import chok.devwork.springboot.BaseDao;

@Repository(value="ClientVipMemInfoDao")
public class VipMemInfoDao extends BaseDao<VipMemInfo,Long>
{
	@Resource
	private SqlSession sqlSession;

	@Override
	protected SqlSession getSqlSession()
	{
		return sqlSession;
	}
	
	@Override
	public Class<VipMemInfo> getEntityClass()
	{
		return VipMemInfo.class;
	}
	
}
