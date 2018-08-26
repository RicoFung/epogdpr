package com.epo.admin.dao;

import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import chok.devwork.springboot.BaseDao;
import com.epo.admin.entity.VipMemInfo;

@Repository
public class VipMemInfoDao extends BaseDao<VipMemInfo,Long>
{
	@Resource//(name = "firstSqlSessionTemplate")
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
