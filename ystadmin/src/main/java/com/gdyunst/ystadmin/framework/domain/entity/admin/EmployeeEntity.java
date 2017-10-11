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
	private String id;   //序列号
	@Column(name="orgId")
	private String orgId;  //员工所属机构id
	@Column(name="departmentId")
	private String departmentId;  //员工所属部门id
	@Column(name="code")
	private String code;  //员工编号
	@Column(name="realName")
	private String realName;  //员工真实姓名
	@Column(name="mobile")
	private String mobile;  //员工手机号码
	@Column(name="tel")
	private String tel;  //联系电话
	@Column(name="email")
	private String email;  //邮箱地址
	@Column(name="address")
	private String address;  //联系地址
	@Column(name="status")
	private String status;  //状态,0在职,1离职,2停职
	@Column(name="dateJoined")
	private String dateJoined;  //入职日期
	@Column(name="dateLeft")
	private String dateLeft;  //离职日期
	@Column(name="workAge")
	private String workAge;  //工作年龄
	@Column(name="emContact")
	private String emContact;  //紧急联系人姓名
	@Column(name="emTel")
	private String emTel;  //紧急联系电话
	@Column(name="creater")
	private String creater;  //创建人id
	@Column(name="createDate")
	private String createDate;  //创建时间
	@Column(name="operator")
	private String operator;  //最后修改人id
	@Column(name="operateDate")
	private String operateDate;  //最后修改日期
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

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getDateJoined(){
		return dateJoined;
	}

	public void setDateJoined(String dateJoined){
		this.dateJoined = dateJoined;
	}

	public String getDateLeft(){
		return dateLeft;
	}

	public void setDateLeft(String dateLeft){
		this.dateLeft = dateLeft;
	}

	public String getWorkAge(){
		return workAge;
	}

	public void setWorkAge(String workAge){
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

	public String getCreateDate(){
		return createDate;
	}

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	public String getOperator(){
		return operator;
	}

	public void setOperator(String operator){
		this.operator = operator;
	}

	public String getOperateDate(){
		return operateDate;
	}

	public void setOperateDate(String operateDate){
		this.operateDate = operateDate;
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
