package com.epo.admin.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.common.Dict;
import com.epo.admin.dao.VipMemInfoDao;
import com.epo.admin.dao.VipMemInfoErrDao;
import com.epo.admin.entity.VipMemInfo;
import com.epo.admin.entity.VipMemInfoErr;

import chok.devwork.springboot.BaseDao;
import chok.devwork.springboot.BaseService;
import chok.util.POIUtil;
import chok.util.UniqueId;
import chok.util.ValidationUtil;

@Service
public class VipMemInfoService extends BaseService<VipMemInfo, Long>
{
	@Autowired
	private VipMemInfoDao dao;
	@Autowired
	private VipMemInfoErrDao dao2;

	@Override
	public BaseDao<VipMemInfo, Long> getEntityDao()
	{
		return dao;
	}

	public Map<String, List<VipMemInfo>> imp2(CommonsMultipartFile files[]) throws Exception
	{
		List<String[]> list = POIUtil.readExcel(files[0]);
		Map<String, List<VipMemInfo>> resultMap = validateRows(list);
		if (resultMap.get("sucRows").size() > 0)
		{
			List<VipMemInfo> rows = resultMap.get("sucRows");
			for (int j = 0; j < rows.size(); j++)
			{
				VipMemInfo po = rows.get(j);
				dao.add(po);
			}
		}
		if (resultMap.get("errRows").size() > 0)
		{
			List<VipMemInfo> rows = resultMap.get("errRows");
			long rowid = UniqueId.genId();
			for (int j = 0; j < rows.size(); j++)
			{
				VipMemInfoErr po = new VipMemInfoErr();
				po.setMemberCode(rows.get(j).getMemberCode());
				po.setEmail(rows.get(j).getEmail());
				po.setJoinDate(rows.get(j).getJoinDate());
				po.setStoreCode(rows.get(j).getStoreCode());
				po.setCountry(rows.get(j).getCountry());
				po.setMsg(rows.get(j).getMsg());
				po.setPassed(rows.get(j).getPassed());
				po.setRowid(rowid);
				dao2.add(po);
			}
		}
		return resultMap;
	}

	/**
	 * 校验Row
	 * @param rows
	 * @return Map<String, List<VipMemInfo>>
	 * @throws Exception 
	 */
	private Map<String, List<VipMemInfo>> validateRows(List<String[]> rows) throws Exception
	{
		List<VipMemInfo> sucRows = new LinkedList<VipMemInfo>();
		List<VipMemInfo> errRows = new LinkedList<VipMemInfo>();
		
		for (int j = 0; j < rows.size(); j++)
		{
			String[] r = rows.get(j);
			VipMemInfo po = new VipMemInfo();
			po.setMemberCode(r[0]);
			po.setEmail(r[1]);
			po.setJoinDate(r[2]);
			po.setStoreCode(r[4]);
			po.setCountry(Dict.countryMap.get(r[5]));
			if (validateCell(po))
				sucRows.add(po);
			else
				errRows.add(po);
		}

		Map<String, List<VipMemInfo>> result = new HashMap<String, List<VipMemInfo>>()
		{
			private static final long serialVersionUID = 1L;
			{
				put("sucRows", sucRows);
				put("errRows", errRows);
			}
		};
		return result;
	}

	/**
	 * 校验Cell
	 * @param po
	 * @return boolean
	 */
	private boolean validateCell(VipMemInfo po)
	{
		if (StringUtils.isBlank(po.getMemberCode()))
		{
			po.setPassed(false);
			po.setMsg(po.getMsg() + "(MemberCode can not be empty)");
		}
		else
		{
			int count = dao.getCount(new HashMap<String, Object>()
			{
				private static final long serialVersionUID = 1L;
				{
					put("memberCode2", po.getMemberCode());
				}
			});
			if (count > 0)
			{
				po.setPassed(false);
				po.setMsg(po.getMsg() + "(MemberCode already exists)");
			}
		}

		if (StringUtils.isBlank(po.getEmail()))
		{
			po.setPassed(false);
			po.setMsg(po.getMsg() + "(Email can not be empty)");
		}
		else
		{
			if (!ValidationUtil.checkEmailFormat(po.getEmail()))
			{
				po.setPassed(false);
				po.setMsg(po.getMsg() + "(Email format is illegal)");
			}
			int count = dao.getCount(new HashMap<String, Object>()
			{
				private static final long serialVersionUID = 1L;
				{
					put("email2", po.getEmail());
				}
			});
			if (count > 0)
			{
				po.setPassed(false);
				po.setMsg(po.getMsg() + "(Email already exists)");
			}
		}

		if (StringUtils.isBlank(po.getJoinDate()))
		{
			po.setPassed(false);
			po.setMsg(po.getMsg() + "(JoinDate can not be empty)");
		}
		else
		{
			if (!ValidationUtil.checkDateFormat(po.getJoinDate()))
			{
				po.setPassed(false);
				po.setMsg(po.getMsg() + "(JoinDate format is illegal)");
			}
		}

		if (StringUtils.isBlank(po.getStoreCode()))
		{
			po.setPassed(false);
			po.setMsg(po.getMsg() + "(StoreCode can not be empty)");
		}

		if (StringUtils.isBlank(po.getCountry()))
		{
			po.setPassed(false);
			po.setMsg(po.getMsg() + "(Country can not be empty)");
		}
		return po.getPassed();
	}
}
