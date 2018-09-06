package com.epo.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.epo.admin.entity.VipMemInfo;

import chok.devwork.springboot.BaseDao;
import chok.util.TimeUtil;

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
	
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> getCountrys()
	{
		return (List<Map<String, String>>) query("getCountrys", null);
	}
	
	public void del(String memberCode)
	{
		this.del("del", memberCode);
	}
	
	public void updSendStatusByMemberCode(String sendStatus, String memberCode)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sendTime", TimeUtil.getCurrentTime());
		param.put("sendStatus", sendStatus);
		param.put("memberCode", memberCode);
		this.upd("updSendStatusByMemberCode", param);
	}
}
