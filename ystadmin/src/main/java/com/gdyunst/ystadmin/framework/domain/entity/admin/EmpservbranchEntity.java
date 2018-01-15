package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:保存员工,网点关联数据,一对多关联;
 */
@Table(name="admin_empservbranch")
public class EmpservbranchEntity extends BaseCrud<EmpservbranchEntity>
{
	@Id
	private String id;   //序列号
	@Column(name="serviceBranchId")
	private String serviceBranchId;  //网点id
	@Column(name="employeeId")
	private String employeeId;  //员工id
	
	
	//get  set 方法
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getServiceBranchId(){
		return serviceBranchId;
	}

	public void setServiceBranchId(String serviceBranchId){
		this.serviceBranchId = serviceBranchId;
	}

	public String getEmployeeId(){
		return employeeId;
	}

	public void setEmployeeId(String employeeId){
		this.employeeId = employeeId;
	}

	
	

	
	
}
