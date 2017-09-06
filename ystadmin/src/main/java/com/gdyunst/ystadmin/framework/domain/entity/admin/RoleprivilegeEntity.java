package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:保存角色拥有权限数据,即角色可访问子系统,菜单,功能数据;
 */
@Table(name="admin_roleprivilege")
public class RoleprivilegeEntity extends BaseCrud<RoleprivilegeEntity>
{
	@Id
	private String id;   //id
	@Column(name="subSystemId")
	private String subSystemId;  //subSystemId
	@Column(name="privilegeId")
	private String privilegeId;  //privilegeId
	@Column(name="roleId")
	private String roleId;  //roleId
	@Column(name="type")
	private Integer type;  //type
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="createDate")
	private Date createDate;  //createDate
	
	
	//get  set 方法
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getSubSystemId(){
		return subSystemId;
	}

	public void setSubSystemId(String subSystemId){
		this.subSystemId = subSystemId;
	}

	public String getPrivilegeId(){
		return privilegeId;
	}

	public void setPrivilegeId(String privilegeId){
		this.privilegeId = privilegeId;
	}

	public String getRoleId(){
		return roleId;
	}

	public void setRoleId(String roleId){
		this.roleId = roleId;
	}

	public Integer getType(){
		return type;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	
	

	
	
}
