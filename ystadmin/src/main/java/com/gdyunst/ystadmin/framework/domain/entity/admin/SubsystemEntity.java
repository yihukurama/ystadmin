package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:保存子系统数据;
 */
@Table(name="admin_subsystem")
public class SubsystemEntity extends BaseCrud<SubsystemEntity>
{
	@Id
	private String id;   //id
	@Column(name="code")
	private String code;  //code
	@Column(name="text")
	private String text;  //text
	@Column(name="url")
	private String url;  //url
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

	public String getText(){
		return text;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getUrl(){
		return url;
	}

	public void setUrl(String url){
		this.url = url;
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

	
	

	
	
}
