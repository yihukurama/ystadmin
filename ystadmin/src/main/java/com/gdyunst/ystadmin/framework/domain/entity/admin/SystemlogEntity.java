package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:保存系统日志数据;
 */
@Table(name="admin_systemlog")
public class SystemlogEntity extends BaseCrud<SystemlogEntity>
{
	@Id
	private String id;   //序列号
	@Column(name="employeeId")
	private String employeeId;  //操作员工id
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="operateDate")
	private Date operateDate;  //操作时间
	@Column(name="content")
	private String content;  //日志内容
	@Column(name="interfaceAddress")
	private String interfaceAddress;  //访问接口地址
	@Column(name="requestIP")
	private String requestIP;  //接口返回结果
	@Column(name="note")
	private String note;  //备注信息
	@Column(name="delStatus")
	private Integer delStatus;  //删除状态,0正常,1删除
	
	
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

	public Date getOperateDate(){
		return operateDate;
	}

	public void setOperateDate(Date operateDate){
		this.operateDate = operateDate;
	}

	public String getContent(){
		return content;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getInterfaceAddress(){
		return interfaceAddress;
	}

	public void setInterfaceAddress(String interfaceAddress){
		this.interfaceAddress = interfaceAddress;
	}

	public String getRequestIP(){
		return requestIP;
	}

	public void setRequestIP(String requestIP){
		this.requestIP = requestIP;
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
