package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:保存机构下属部门数据;
 */
@Table(name="admin_department")
public class DepartmentEntity extends BaseCrud<DepartmentEntity>
{
	@Id
	private String id;   //序列号
	@Column(name="orgId")
	private String orgId;  //部门所属机构id
	@Column(name="code")
	private String code;  //部门编码
	@Column(name="text")
	private String text;  //部门名称
	@Column(name="tel")
	private String tel;  //联系电话
	@Column(name="principalId")
	private String principalId;  //部门负责人id
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
	@Column(name="delStatus")
	private String delStatus;  //删除状态,0未删除,1删除
	
	
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

	public String getDelStatus(){
		return delStatus;
	}

	public void setDelStatus(String delStatus){
		this.delStatus = delStatus;
	}

	
	

	
	
}
