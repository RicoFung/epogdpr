package com.epo.admin.entity;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import com.common.Dict;

import chok.util.CollectionUtil;
import chok.util.TimeUtil;

/**
 *
 * @author rico
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class VipMemInfo implements java.io.Serializable
{
	// 会员代码 db_column: member_code
	private java.lang.String memberCode;
	// email db_column: email
	private java.lang.String email;
	// 会员加入日期 db_column: join_date
	private java.lang.String joinDate;
	// 会员所在店铺编码 db_column: store_code
	private java.lang.String storeCode;
	// 所在国家编码，采用Java 的 Locate 数据 db_column: country
	private java.lang.String country;
	// rowid db_column: rowid
	private long rowid;
	// 是否验证通过
	private boolean passed = true;
	// 错误信息
	private String msg = "";
	// VipPolicyFeedback
	private List<VipPolicyFeedback> vipPolicyFeedbacks;

	public void setMemberCode(java.lang.String value)
	{
		this.memberCode = value;
	}

	public java.lang.String getMemberCode()
	{
		return this.memberCode;
	}

	public void setEmail(java.lang.String value)
	{
		this.email = value;
	}

	public java.lang.String getEmail()
	{
		return this.email;
	}

	public void setJoinDate(java.lang.String value)
	{
		this.joinDate = value;
	}

	public java.lang.String getJoinDate()
	{
		return this.joinDate;
	}
	
	public java.lang.String getJoinDate2()
	{
		String result = "";
		try
		{
			result = TimeUtil.toggleFormat(this.joinDate, Dict.DATE_APP_FORMAT, Locale.CHINA, Dict.DATE_EXCEL_FORMAT, Locale.ENGLISH);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public void setStoreCode(java.lang.String value)
	{
		this.storeCode = value;
	}

	public java.lang.String getStoreCode()
	{
		return this.storeCode;
	}

	public void setCountry(java.lang.String value)
	{
		this.country = value;
	}

	public java.lang.String getCountry()
	{
		return this.country;
	}
	
	public java.lang.String getCountryCn()
	{
		return CollectionUtil.getKey(Dict.countryMap, this.country).get(0).toString();
	}

	public boolean getPassed()
	{
		return passed;
	}

	public void setPassed(boolean passed)
	{
		this.passed = passed;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public long getRowid()
	{
		return rowid;
	}

	public void setRowid(long rowid)
	{
		this.rowid = rowid;
	}

	public List<VipPolicyFeedback> getVipPolicyFeedbacks()
	{
		return vipPolicyFeedbacks;
	}

	public void setVipPolicyFeedbacks(List<VipPolicyFeedback> vipPolicyFeedbacks)
	{
		this.vipPolicyFeedbacks = vipPolicyFeedbacks;
	}

	@Override
	public String toString()
	{
		return "VipMemInfo [memberCode=" + memberCode + ", email=" + email + ", joinDate=" + joinDate + ", storeCode="
				+ storeCode + ", country=" + country + ", passed=" + passed + ", msg=" + msg + "]";
	}

}
