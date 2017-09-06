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
@Table(name="service_caseuser")
public class CaseuserEntity extends BaseCrud<CaseuserEntity>
{
	@Id
	private String id;   //id
	@Column(name="caseId")
	private String caseId;  //caseId
	@Column(name="userId")
	private String userId;  //userId
	@Column(name="type")
	private Integer type;  //type
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="createDate")
	private Date createDate;  //createDate
	@Column(name="point")
	private Integer point;  //point
	@Column(name="realName")
	private String realName;  //realName
	
	
	//get  set 方法
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getCaseId(){
		return caseId;
	}

	public void setCaseId(String caseId){
		this.caseId = caseId;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserId(String userId){
		this.userId = userId;
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

	public Integer getPoint(){
		return point;
	}

	public void setPoint(Integer point){
		this.point = point;
	}

	public String getRealName(){
		return realName;
	}

	public void setRealName(String realName){
		this.realName = realName;
	}

	
	

	
	
}
