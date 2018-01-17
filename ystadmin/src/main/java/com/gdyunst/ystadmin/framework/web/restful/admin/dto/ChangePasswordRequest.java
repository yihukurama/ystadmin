package com.gdyunst.ystadmin.framework.web.restful.admin.dto;

/**
 * 功能描述:重置、修改账号密码数据封装;
 * @Author:liujun
 * @Date:2017年1月13日 下午7:56:19
 */
public class ChangePasswordRequest
{
	private String userId;       // 账号id;
	private String oldPassword;  // 原密码;
	private String newPassword;  // 新密码;
	
	// 构造方法;
	public ChangePasswordRequest(){}
	
	// Getter and Setter;
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getOldPassword()
	{
		return oldPassword;
	}
	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
	}
	public String getNewPassword()
	{
		return newPassword;
	}
	public void setNewPassword(String newPassword)
	{
		this.newPassword = newPassword;
	}
	
}
