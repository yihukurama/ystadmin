package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:
 */
@Table(name="service_pointuser")
public class PointuserEntity extends BaseCrud<PointuserEntity>
{
	@Id
	private String id;   //id
	@Column(name="userId")
	private String userId;  //userId
	@Column(name="currentPoint")
	private String currentPoint;  //currentPoint
	@Column(name="delStatus")
	private String delStatus;  //delStatus
	@Column(name="createDate")
	private String createDate;  //createDate
	
	
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

	public String getCurrentPoint(){
		return currentPoint;
	}

	public void setCurrentPoint(String currentPoint){
		this.currentPoint = currentPoint;
	}

	public String getDelStatus(){
		return delStatus;
	}

	public void setDelStatus(String delStatus){
		this.delStatus = delStatus;
	}

	public String getCreateDate(){
		return createDate;
	}

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	
	

	
	
}
