package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:保存账号,角色关联数据,一对多关联;
 */
@Table(name="admin_userrole")
public class UserroleEntity extends BaseCrud<UserroleEntity>
{
	@Id
	private String id;   //序列号
	@Column(name="userId")
	private String userId;  //账号id
	@Column(name="roleId")
	private String roleId;  //角色id
	@Column(name="createDate")
	private String createDate;  //创建时间
	
	
	//get  set 方法
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getRoleId(){
		return roleId;
	}

	public void setRoleId(String roleId){
		this.roleId = roleId;
	}

	public String getCreateDate(){
		return createDate;
	}

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	
	

	
	
}
