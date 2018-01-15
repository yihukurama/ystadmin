package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:保存评估网点数据;
 */
@Table(name="admin_servicebranch")
public class ServicebranchEntity extends BaseCrud<ServicebranchEntity>
{
	@Id
	private String id;   //序列号
	@Column(name="orgId")
	private String orgId;  //网点所属机构id
	@Column(name="departmentId")
	private String departmentId;  //网点所属部门id
	@Column(name="code")
	private String code;  //网点代码
	@Column(name="text")
	private String text;  //网点名称
	@Column(name="areaId")
	private String areaId;  //网点所在区域id
	@Column(name="areaText")
	private String areaText;  //网点所在区域地址
	@Column(name="address")
	private String address;  //网点详细地址
	@Column(name="tel")
	private String tel;  //网点联系电话
	@Column(name="principalId")
	private String principalId;  //网点负责人id
	@Column(name="principal")
	private String principal;  //负责人姓名
	@Column(name="indexOrder")
	private Integer indexOrder;  //排序字段
	@Column(name="lng")
	private Double lng;  //网点所在经度
	@Column(name="lat")
	private Double lat;  //网点所在纬度
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
	
	
	//get  set 方法
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getOrgId(){
		return orgId;
	}

	public void setOrgId(String orgId){
		this.orgId = orgId;
	}

	public String getDepartmentId(){
		return departmentId;
	}

	public void setDepartmentId(String departmentId){
		this.departmentId = departmentId;
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

	public String getPrincipal(){
		return principal;
	}

	public void setPrincipal(String principal){
		this.principal = principal;
	}

	public Integer getIndexOrder(){
		return indexOrder;
	}

	public void setIndexOrder(Integer indexOrder){
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

	
	

	
	
}
