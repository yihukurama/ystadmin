package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:保存全部人员账号数据;
 */
@Table(name="admin_user")
public class UserEntity extends BaseCrud<UserEntity>
{
	@Id
	private String id;   //序列号
	@Column(name="employeeName")
	private String employeeName;  //员工姓名
	@Column(name="employeeId")
	private String employeeId;  //账号关联员工id
	@Column(name="username")
	private String username;  //账号名称
	@Column(name="password")
	private String password;  //账号密码
	@Column(name="rolelist")
	private String rolelist;  //拥有的角色名，用逗号区分
	@Column(name="status")
	private Integer status;  //账号状态,1正常,2禁用
	@Column(name="type")
	private Integer type;  //账号类型,1,评估、报价员工,2,保险,3,交警,4,法院
	@Column(name="createrId")
	private String createrId;  //创建人id
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="createDate")
	private Date createDate;  //创建时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="lastLoginDate")
	private Date lastLoginDate;  //最后登录日期
	@Column(name="loginCount")
	private Integer loginCount;  //登录次数统计
	@Column(name="ipAddr")
	private String ipAddr;  //用户最后登录时的ip地址
	@Column(name="indexOrder")
	private Integer indexOrder;  //排序字段
	@Column(name="note")
	private String note;  //备注信息
	@Column(name="delStatus")
	private Integer delStatus;  //删除状态,0正常,1删除
	@Column(name="token")
	private String token;  //协警APP用的token
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="operateDate")
	private Date operateDate;  //最后修改时间
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

	public String getEmployeeName(){
		return employeeName;
	}

	public void setEmployeeName(String employeeName){
		this.employeeName = employeeName;
	}

	public String getEmployeeId(){
		return employeeId;
	}

	public void setEmployeeId(String employeeId){
		this.employeeId = employeeId;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getRolelist(){
		return rolelist;
	}

	public void setRolelist(String rolelist){
		this.rolelist = rolelist;
	}

	public Integer getStatus(){
		return status;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getType(){
		return type;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public String getCreaterId(){
		return createrId;
	}

	public void setCreaterId(String createrId){
		this.createrId = createrId;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public Date getLastLoginDate(){
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate){
		this.lastLoginDate = lastLoginDate;
	}

	public Integer getLoginCount(){
		return loginCount;
	}

	public void setLoginCount(Integer loginCount){
		this.loginCount = loginCount;
	}

	public String getIpAddr(){
		return ipAddr;
	}

	public void setIpAddr(String ipAddr){
		this.ipAddr = ipAddr;
	}

	public Integer getIndexOrder(){
		return indexOrder;
	}

	public void setIndexOrder(Integer indexOrder){
		this.indexOrder = indexOrder;
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

	public String getToken(){
		return token;
	}

	public void setToken(String token){
		this.token = token;
	}

	public Date getOperateDate(){
		return operateDate;
	}

	public void setOperateDate(Date operateDate){
		this.operateDate = operateDate;
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
