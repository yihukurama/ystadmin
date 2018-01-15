package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.TreeCrud;

/**
 * 功能描述:保存系统菜单数据;
 */
@Table(name="admin_menu")
public class MenuEntity extends TreeCrud<MenuEntity>
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
	@Column(name="note")
	private String note;  //备注信息
	@Column(name="delStatus")
	private Integer delStatus;  //删除状态,0未删除,1删除
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="createDate")
	private Date createDate;  //创建时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="operateDate")
	private Date operateDate;  //最后修改时间
	@Column(name="createrId")
	private String createrId;  //创建人Id
	@Column(name="creater")
	private String creater;  //创建人姓名
	@Column(name="operatorId")
	private String operatorId;  //最后更新人Id
	@Column(name="operator")
	private String operator;  //最后更新人姓名
	@Column(name="indexOrder")
	private Integer indexOrder;  //排序字段
	
	
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

	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public Date getOperateDate(){
		return operateDate;
	}

	public void setOperateDate(Date operateDate){
		this.operateDate = operateDate;
	}

	public String getCreaterId(){
		return createrId;
	}

	public void setCreaterId(String createrId){
		this.createrId = createrId;
	}

	public String getCreater(){
		return creater;
	}

	public void setCreater(String creater){
		this.creater = creater;
	}

	public String getOperatorId(){
		return operatorId;
	}

	public void setOperatorId(String operatorId){
		this.operatorId = operatorId;
	}

	public String getOperator(){
		return operator;
	}

	public void setOperator(String operator){
		this.operator = operator;
	}

	public Integer getIndexOrder(){
		return indexOrder;
	}

	public void setIndexOrder(Integer indexOrder){
		this.indexOrder = indexOrder;
	}

	
	

	
	
}
