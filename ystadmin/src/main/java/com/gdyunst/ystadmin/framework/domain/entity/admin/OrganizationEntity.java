package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:保存评估机构数据;
 */
@Table(name="admin_organization")
public class OrganizationEntity extends BaseCrud<OrganizationEntity>
{
	@Id
	private String id;   //id
	@Column(name="parentId")
	private String parentId;  //parentId
	@Column(name="path")
	private String path;  //path
	@Column(name="code")
	private String code;  //code
	@Column(name="text")
	private String text;  //text
	@Column(name="areaId")
	private String areaId;  //areaId
	@Column(name="areaText")
	private String areaText;  //areaText
	@Column(name="address")
	private String address;  //address
	@Column(name="tel")
	private String tel;  //tel
	@Column(name="principalId")
	private String principalId;  //principalId
	@Column(name="indexOrder")
	private Integer indexOrder;  //indexOrder
	@Column(name="lng")
	private Double lng;  //lng
	@Column(name="lat")
	private Double lat;  //lat
	@Column(name="note")
	private String note;  //note
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
	@Column(name="printQuotor")
	private Boolean printQuotor;  //printQuotor
	@Column(name="delStatus")
	private Integer delStatus;  //delStatus
	
	
	//get  set 方法
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
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

	public String getAreaId(){
		return areaId;
	}

	public void setAreaId(String areaId){
		this.areaId = areaId;
	}

	public String getAreaText(){
		return areaText;
	}

	public void setAreaText(String areaText){
		this.areaText = areaText;
	}

	public String getAddress(){
		return address;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getTel(){
		return tel;
	}

	public void setTel(String tel){
		this.tel = tel;
	}

	public String getPrincipalId(){
		return principalId;
	}

	public void setPrincipalId(String principalId){
		this.principalId = principalId;
	}

	public Integer getIndexOrder(){
		return indexOrder;
	}

	public void setIndexOrder(Integer indexOrder){
		this.indexOrder = indexOrder;
	}

	public Double getLng(){
		return lng;
	}

	public void setLng(Double lng){
		this.lng = lng;
	}

	public Double getLat(){
		return lat;
	}

	public void setLat(Double lat){
		this.lat = lat;
	}

	public String getNote(){
		return note;
	}

	public void setNote(String note){
		this.note = note;
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

	public Boolean getPrintQuotor(){
		return printQuotor;
	}

	public void setPrintQuotor(Boolean printQuotor){
		this.printQuotor = printQuotor;
	}

	public Integer getDelStatus(){
		return delStatus;
	}

	public void setDelStatus(Integer delStatus){
		this.delStatus = delStatus;
	}

	
	

	
	
}
