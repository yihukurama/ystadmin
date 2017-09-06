package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:保存系统菜单数据;
 */
@Table(name="admin_menu")
public class MenuEntity extends BaseCrud<MenuEntity>
{
	@Id
	private String id;   //id
	@Column(name="subSystemId")
	private String subSystemId;  //subSystemId
	@Column(name="parentId")
	private String parentId;  //parentId
	@Column(name="path")
	private String path;  //path
	@Column(name="code")
	private String code;  //code
	@Column(name="text")
	private String text;  //text
	@Column(name="linkUrl")
	private String linkUrl;  //linkUrl
	@Column(name="serverUrl")
	private String serverUrl;  //serverUrl
	@Column(name="iconClass")
	private String iconClass;  //iconClass
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

	public String getSubSystemId(){
		return subSystemId;
	}

	public void setSubSystemId(String subSystemId){
		this.subSystemId = subSystemId;
	}

	public String getParentId(){
		return parentId;
	}

	public void setParentId(String parentId){
		this.parentId = parentId;
	}

	public String getPath(){
		return path;
	}

	public void setPath(String path){
		this.path = path;
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

	public String getLinkUrl(){
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl){
		this.linkUrl = linkUrl;
	}

	public String getServerUrl(){
		return serverUrl;
	}

	public void setServerUrl(String serverUrl){
		this.serverUrl = serverUrl;
	}

	public String getIconClass(){
		return iconClass;
	}

	public void setIconClass(String iconClass){
		this.iconClass = iconClass;
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
