package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:保存公司评估、报价人员数据;
 */
@Table(name="admin_employee")
public class EmployeeEntity extends BaseCrud<EmployeeEntity>
{
	@Id
	private String id;   //id
	@Column(name="orgId")
	private String orgId;  //orgId
	@Column(name="departmentId")
	private String departmentId;  //departmentId
	@Column(name="code")
	private String code;  //code
	@Column(name="realName")
	private String realName;  //realName
	@Column(name="mobile")
	private String mobile;  //mobile
	@Column(name="tel")
	private String tel;  //tel
	@Column(name="email")
	private String email;  //email
	@Column(name="address")
	private String address;  //address
	@Column(name="status")
	private Integer status;  //status
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="dateJoined")
	private Date dateJoined;  //dateJoined
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="dateLeft")
	private Date dateLeft;  //dateLeft
	@Column(name="workAge")
	private Integer workAge;  //workAge
	@Column(name="emContact")
	private String emContact;  //emContact
	@Column(name="emTel")
	private String emTel;  //emTel
	@Column(name="creater")
	private String creater;  //creater
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="createDate")
	private Date createDate;  //createDate
	@Column(name="operator")
	private String operator;  //operator
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="operateDate")
	private Date operateDate;  //operateDate
	@Column(name="indexOrder")
	private Integer indexOrder;  //indexOrder
	@Column(name="note")
	private String note;  //note
	@Column(name="delStatus")
	private Integer delStatus;  //delStatus
	
	
	//get  set 方法
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getOrgId(){
		return orgId;
	}

	public void setOrgId(String orgId){
		this.orgId = orgId;
	}

	public String getDepartmentId(){
		return departmentId;
	}

	public void setDepartmentId(String departmentId){
		this.departmentId = departmentId;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getRealName(){
		return realName;
	}

	public void setRealName(String realName){
		this.realName = realName;
	}

	public String getMobile(){
		return mobile;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getTel(){
		return tel;
	}

	public void setTel(String tel){
		this.tel = tel;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getAddress(){
		return address;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public Integer getStatus(){
		return status;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Date getDateJoined(){
		return dateJoined;
	}

	public void setDateJoined(Date dateJoined){
		this.dateJoined = dateJoined;
	}

	public Date getDateLeft(){
		return dateLeft;
	}

	public void setDateLeft(Date dateLeft){
		this.dateLeft = dateLeft;
	}

	public Integer getWorkAge(){
		return workAge;
	}

	public void setWorkAge(Integer workAge){
		this.workAge = workAge;
	}

	public String getEmContact(){
		return emContact;
	}

	public void setEmContact(String emContact){
		this.emContact = emContact;
	}

	public String getEmTel(){
		return emTel;
	}

	public void setEmTel(String emTel){
		this.emTel = emTel;
	}

	public String getCreater(){
		return creater;
	}

	public void setCreater(String creater){
		this.creater = creater;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public String getOperator(){
		return operator;
	}

	public void setOperator(String operator){
		this.operator = operator;
	}

	public Date getOperateDate(){
		return operateDate;
	}

	public void setOperateDate(Date operateDate){
		this.operateDate = operateDate;
	}

	public Integer getIndexOrder(){
		return indexOrder;
	}

	public void setIndexOrder(Integer indexOrder){
		this.indexOrder = indexOrder;
	}

	public String getNote(){
		return note;
	}

	public void setNote(String note){
		this.note = note;
	}

	public Integer getDelStatus(){
		return delStatus;
	}

	public void setDelStatus(Integer delStatus){
		this.delStatus = delStatus;
	}

	
	

	
	
}
