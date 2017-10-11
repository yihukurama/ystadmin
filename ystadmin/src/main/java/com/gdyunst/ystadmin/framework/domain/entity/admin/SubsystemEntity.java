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
	private String id;   //序列号
	@Column(name="code")
	private String code;  //子系统编码
	@Column(name="text")
	private String text;  //子系统名称
	@Column(name="url")
	private String url;  //子系统URL链接地址
	@Column(name="indexOrder")
	private String indexOrder;  //排序字段
	@Column(name="creater")
	private String creater;  //创建人id
	@Column(name="createDate")
	private String createDate;  //创建日期
	@Column(name="operator")
	private String operator;  //最后修改人id
	@Column(name="operateDate")
	private String operateDate;  //最后修改日期
	@Column(name="note")
	private String note;  //备注信息
	
	
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

	public String getIndexOrder(){
		return indexOrder;
	}

	public void setIndexOrder(String indexOrder){
		this.indexOrder = indexOrder;
	}

	public String getCreater(){
		return creater;
	}

	public void setCreater(String creater){
		this.creater = creater;
	}

	public String getCreateDate(){
		return createDate;
	}

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	public String getOperator(){
		return operator;
	}

	public void setOperator(String operator){
		this.operator = operator;
	}

	public String getOperateDate(){
		return operateDate;
	}

	public void setOperateDate(String operateDate){
		this.operateDate = operateDate;
	}

	public String getNote(){
		return note;
	}

	public void setNote(String note){
		this.note = note;
	}

	
	

	
	
}
