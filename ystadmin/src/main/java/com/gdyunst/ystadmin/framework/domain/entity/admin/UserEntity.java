package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:保存全部人员账号数据;
 */
@Table(name="admin_user")
public class UserEntity extends BaseCrud<UserEntity>
{
	@Id
	private String id;   //序列号
	@Column(name="employeeId")
	private String employeeId;  //账号关联员工id
	@Column(name="username")
	private String username;  //账号名称
	@Column(name="password")
	private String password;  //账号密码
	@Column(name="status")
	private String status;  //账号状态,1正常,2禁用
	@Column(name="type")
	private String type;  //账号类型,1,评估、报价员工,2,保险,3,交警,4,法院
	@Column(name="creater")
	private String creater;  //创建人id
	@Column(name="createDate")
	private String createDate;  //创建时间
	@Column(name="lastLoginDate")
	private String lastLoginDate;  //最后登录日期
	@Column(name="loginCount")
	private String loginCount;  //登录次数统计
	@Column(name="ipAddr")
	private String ipAddr;  //用户最后登录时的ip地址
	@Column(name="indexOrder")
	private String indexOrder;  //排序字段
	@Column(name="note")
	private String note;  //备注信息
	@Column(name="delStatus")
	private String delStatus;  //删除状态,0正常,1删除
	
	
	//get  set 方法
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getEmployeeId(){
		return employeeId;
	}

	public void setEmployeeId(String employeeId){
		this.employeeId = employeeId;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getCreater(){
		return creater;
	}

	public void setCreater(String creater){
		this.creater = creater;
	}

	public String getCreateDate(){
		return createDate;
	}

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	public String getLastLoginDate(){
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate){
		this.lastLoginDate = lastLoginDate;
	}

	public String getLoginCount(){
		return loginCount;
	}

	public void setLoginCount(String loginCount){
		this.loginCount = loginCount;
	}

	public String getIpAddr(){
		return ipAddr;
	}

	public void setIpAddr(String ipAddr){
		this.ipAddr = ipAddr;
	}

	public String getIndexOrder(){
		return indexOrder;
	}

	public void setIndexOrder(String indexOrder){
		this.indexOrder = indexOrder;
	}

	public String getNote(){
		return note;
	}

	public void setNote(String note){
		this.note = note;
	}

	public String getDelStatus(){
		return delStatus;
	}

	public void setDelStatus(String delStatus){
		this.delStatus = delStatus;
	}

	
	

	
	
}
