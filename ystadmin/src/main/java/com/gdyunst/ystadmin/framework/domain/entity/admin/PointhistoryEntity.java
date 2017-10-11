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
	private String type;  //获得或消费积分的各种类型
	@Column(name="createDate")
	private String createDate;  //createDate
	@Column(name="value")
	private String value;  //积分数量
	@Column(name="delStatus")
	private String delStatus;  //delStatus
	@Column(name="tag")
	private String tag;  //1处理案件  2消费
	@Column(name="currentPoint")
	private String currentPoint;  //当前积分
	@Column(name="relatedId")
	private String relatedId;  //tag为1时是案件id   tag为2时是奖品id
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

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getCreateDate(){
		return createDate;
	}

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	public String getValue(){
		return value;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getDelStatus(){
		return delStatus;
	}

	public void setDelStatus(String delStatus){
		this.delStatus = delStatus;
	}

	public String getTag(){
		return tag;
	}

	public void setTag(String tag){
		this.tag = tag;
	}

	public String getCurrentPoint(){
		return currentPoint;
	}

	public void setCurrentPoint(String currentPoint){
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
