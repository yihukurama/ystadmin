package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:保存系统通用配置数据;
 */
@Table(name="admin_configuration")
public class ConfigurationEntity extends BaseCrud<ConfigurationEntity>
{
	@Id
	private String id;   //id
	@Column(name="code")
	private String code;  //code
	@Column(name="value")
	private String value;  //value
	@Column(name="type")
	private Integer type;  //type
	@Column(name="indexOrder")
	private Integer indexOrder;  //indexOrder
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

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getValue(){
		return value;
	}

	public void setValue(String value){
		this.value = value;
	}

	public Integer getType(){
		return type;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public Integer getIndexOrder(){
		return indexOrder;
	}

	public void setIndexOrder(Integer indexOrder){
		this.indexOrder = indexOrder;
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
