package com.epo.admin.entity;

/**
 *
 * @author rico
 * @version 1.0
 * @since 1.0
 * */
public class VipPolicyFeedback implements java.io.Serializable
{
    // 标识       db_column: id_feedback 
	private java.lang.Long idFeedback;
    // 会员代码       db_column: member_code 
	private java.lang.String memberCode;
    // 反馈结果： 'A'=接受 'R'=拒绝       db_column: feedback_result 
	private java.lang.String feedbackResult;
    // 反馈时间: 记录服务器收到结果时间       db_column: feedback_time 
	private java.lang.String feedbackTime;
    // 通过JS，记录客户端IP       db_column: client_ip 
	private java.lang.String clientIp;
    // 通过JS，记录客户端本地时间       db_column: client_sent_time 
	private java.lang.String clientSentTime;
    // 通过JS，记录客户端浏览器信息       db_column: browser_agent 
	private java.lang.String browserAgent;

	public VipPolicyFeedback(){
	}

	public VipPolicyFeedback(
		java.lang.Long idFeedback,
		java.lang.String memberCode,
		java.lang.String feedbackResult,
		java.lang.String feedbackTime,
		java.lang.String clientIp,
		java.lang.String clientSentTime,
		java.lang.String browserAgent
	)
	{
		this.idFeedback = idFeedback;
		this.memberCode = memberCode;
		this.feedbackResult = feedbackResult;
		this.feedbackTime = feedbackTime;
		this.clientIp = clientIp;
		this.clientSentTime = clientSentTime;
		this.browserAgent = browserAgent;
	}

	public void setIdFeedback(java.lang.Long value) 
	{
		this.idFeedback = value;
	}
	
	public java.lang.Long getIdFeedback() 
	{
		return this.idFeedback;
	}
	public void setMemberCode(java.lang.String value) 
	{
		this.memberCode = value;
	}
	
	public java.lang.String getMemberCode() 
	{
		return this.memberCode;
	}
	public void setFeedbackResult(java.lang.String value) 
	{
		this.feedbackResult = value;
	}
	
	public java.lang.String getFeedbackResult() 
	{
		return this.feedbackResult;
	}
	public void setFeedbackTime(java.lang.String value) 
	{
		this.feedbackTime = value;
	}
	
	public java.lang.String getFeedbackTime() 
	{
		return this.feedbackTime;
	}
	public void setClientIp(java.lang.String value) 
	{
		this.clientIp = value;
	}
	
	public java.lang.String getClientIp() 
	{
		return this.clientIp;
	}
	public void setClientSentTime(java.lang.String value) 
	{
		this.clientSentTime = value;
	}
	
	public java.lang.String getClientSentTime() 
	{
		return this.clientSentTime;
	}
	public void setBrowserAgent(java.lang.String value) 
	{
		this.browserAgent = value;
	}
	
	public java.lang.String getBrowserAgent() 
	{
		return this.browserAgent;
	}
}

