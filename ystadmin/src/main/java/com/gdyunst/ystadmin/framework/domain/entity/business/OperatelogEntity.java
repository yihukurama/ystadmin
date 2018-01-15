package com.gdyunst.ystadmin.framework.domain.entity.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:操作记录表
 */
@Table(name="business_operatelog")
public class OperatelogEntity extends BaseCrud<OperatelogEntity>
{
	@Id
	private String id;   //id
	@Column(name="relateId")
	private String relateId;  //关联id
	@Column(name="type")
	private String type;  //类型 
	@Column(name="creater")
	private String creater;  //操作人id
	@Column(name="realName")
	private String realName;  //处理人真实姓名
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="createDate")
	private Date createDate;  //处理时间
	@Column(name="delStatus")
	private Integer delStatus;  //删除状态,0未读取，1已经读取
	@Column(name="result")
	private Boolean result;  //操作结果
	
	
	//get  set 方法
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getRelateId(){
		return relateId;
	}

	public void setRelateId(String relateId){
		this.relateId = relateId;
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

	public String getRealName(){
		return realName;
	}

	public void setRealName(String realName){
		this.realName = realName;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public Integer getDelStatus(){
		return delStatus;
	}

	public void setDelStatus(Integer delStatus){
		this.delStatus = delStatus;
	}

	public Boolean getResult(){
		return result;
	}

	public void setResult(Boolean result){
		this.result = result;
	}

	
	

	
	
}
