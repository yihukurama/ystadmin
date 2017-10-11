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
	private String id;   //序列号
	@Column(name="subSystemId")
	private String subSystemId;  //菜单所属子系统id
	@Column(name="parentId")
	private String parentId;  //父级菜单id,该值为root代表菜单为一级菜单
	@Column(name="path")
	private String path;  //分层路径
	@Column(name="code")
	private String code;  //菜单编码
	@Column(name="text")
	private String text;  //菜单名称
	@Column(name="linkUrl")
	private String linkUrl;  //extjs链接
	@Column(name="serverUrl")
	private String serverUrl;  //菜单接口地址
	@Column(name="iconClass")
	private String iconClass;  //图标样式
	@Column(name="indexOrder")
	private String indexOrder;  //排序字段
	@Column(name="creater")
	private String creater;  //创建人id
	@Column(name="createDate")
	private String createDate;  //创建时间
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
