package com.gdyunst.ystadmin.framework.domain.repository;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

public abstract class IObject {
	public static Logger LOGGER = LoggerFactory.getLogger(IObject.class);
	protected String id;
	protected Boolean loadRelate = true;	//加载时默认加载关联数据


	public Boolean getLoadRelate() {
		return loadRelate;
	}

	public void setLoadRelate(Boolean loadRelate) {
		this.loadRelate = loadRelate;
	}

	public IObject doGetRelationData() {

		return this;
	}
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="operateDate")
	protected Date operateDate;  //最后修改时间
	@Column(name="createrId")
	protected String createrId;  //创建人Id
	@Column(name="creater")
	protected String creater;  //创建人姓名
	@Column(name="operatorId")
	protected String operatorId;  //最后更新人Id
	@Column(name="operator")
	protected String operator;  //最后更新人姓名

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	protected List<String> ids;

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public IObject(){
		
	}
	
	
	/**
	 * 功能描述:判断id是否合法
	 * @return
	 * @Author:dengshuai
	 * @Date:2017年2月6日 下午2:57:12
	 */
	public boolean hasId(){
		LOGGER.debug("id is {},getId() is {}",id,getId());
		if(getId()==null || getId().equals("") || getId().length()!= 32){
			return false;
		}
		return true;
	}
}
