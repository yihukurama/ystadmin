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
	private String id;   //序列号
	@Column(name="parentId")
	private String parentId;  //父级机构id,默认为root,该机构为一级机构
	@Column(name="path")
	private String path;  //分层路径
	@Column(name="code")
	private String code;  //机构编码
	@Column(name="text")
	private String text;  //机构名称
	@Column(name="areaId")
	private String areaId;  //机构所在区域id
	@Column(name="areaText")
	private String areaText;  //机构所在区域地址
	@Column(name="address")
	private String address;  //机构所在地详细地址
	@Column(name="tel")
	private String tel;  //联系电话
	@Column(name="principalId")
	private String principalId;  //机构负责人id
	@Column(name="indexOrder")
	private String indexOrder;  //排序字段
	@Column(name="lng")
	private Double lng;  //机构所在地经度
	@Column(name="lat")
	private Double lat;  //机构所在地纬度
	@Column(name="note")
	private String note;  //备注信息
	@Column(name="creater")
	private String creater;  //创建人id
	@Column(name="createDate")
	private String createDate;  //创建时间
	@Column(name="operator")
	private String operator;  //最后修改人id
	@Column(name="operateDate")
	private String operateDate;  //最后修改日期
	@Column(name="printQuotor")
	private Boolean printQuotor;  //是否打印报价人姓名,默认不打印
	@Column(name="delStatus")
	private String delStatus;  //删除状态,0未删除,1删除
	
	
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

	public String getIndexOrder(){
		return indexOrder;
	}

	public void setIndexOrder(String indexOrder){
		this.indexOrder = indexOrder;
	}

	public Double getLng(){
		return lng==null?0.0:lng;
	}

	public void setLng(Double lng){
		this.lng = lng==null?0.0:lng;
	}

	public Double getLat(){
		return lat==null?0.0:lat;
	}

	public void setLat(Double lat){
		this.lat = lat==null?0.0:lat;
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

	public Boolean getPrintQuotor(){
		return printQuotor;
	}

	public void setPrintQuotor(Boolean printQuotor){
		this.printQuotor = printQuotor;
	}

	public String getDelStatus(){
		return delStatus;
	}

	public void setDelStatus(String delStatus){
		this.delStatus = delStatus;
	}

	
	

	
	
}
