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
	private String id;   //id
	@Column(name="userId")
	private String userId;  //userId
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="operateDate")
	private Date operateDate;  //operateDate
	@Column(name="content")
	private String content;  //content
	@Column(name="interfaceAddress")
	private String interfaceAddress;  //interfaceAddress
	@Column(name="requestIP")
	private String requestIP;  //requestIP
	@Column(name="note")
	private String note;  //note
	@Column(name="delStatus")
	private Integer delStatus;  //delStatus
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

	public String getUserId(){
		return userId;
	}

	public void setUserId(String userId){
		this.userId = userId;
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

	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	
	

	
	
}
