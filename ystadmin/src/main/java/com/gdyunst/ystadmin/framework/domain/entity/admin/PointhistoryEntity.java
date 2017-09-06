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
@Table(name="service_pointhistory")
public class PointhistoryEntity extends BaseCrud<PointhistoryEntity>
{
	@Id
	private String id;   //id
	@Column(name="type")
	private Integer type;  //type
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="createDate")
	private Date createDate;  //createDate
	@Column(name="value")
	private Integer value;  //value
	@Column(name="delStatus")
	private Integer delStatus;  //delStatus
	@Column(name="tag")
	private Integer tag;  //tag
	@Column(name="currentPoint")
	private Integer currentPoint;  //currentPoint
	@Column(name="relatedId")
	private String relatedId;  //relatedId
	@Column(name="userId")
	private String userId;  //userId
	@Column(name="realName")
	private String realName;  //realName
	
	
	//get  set 方法
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
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

	public Integer getValue(){
		return value;
	}

	public void setValue(Integer value){
		this.value = value;
	}

	public Integer getDelStatus(){
		return delStatus;
	}

	public void setDelStatus(Integer delStatus){
		this.delStatus = delStatus;
	}

	public Integer getTag(){
		return tag;
	}

	public void setTag(Integer tag){
		this.tag = tag;
	}

	public Integer getCurrentPoint(){
		return currentPoint;
	}

	public void setCurrentPoint(Integer currentPoint){
		this.currentPoint = currentPoint;
	}

	public String getRelatedId(){
		return relatedId;
	}

	public void setRelatedId(String relatedId){
		this.relatedId = relatedId;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getRealName(){
		return realName;
	}

	public void setRealName(String realName){
		this.realName = realName;
	}

	
	

	
	
}
