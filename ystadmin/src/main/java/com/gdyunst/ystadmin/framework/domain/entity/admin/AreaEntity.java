package com.gdyunst.ystadmin.framework.domain.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:保存区域数据
 */
@Table(name="admin_area")
public class AreaEntity extends BaseCrud<AreaEntity>
{
	@Id
	private String id;   //id
	@Column(name="parentId")
	private String parentId;  //parentId
	@Column(name="path")
	private String path;  //path
	@Column(name="text")
	private String text;  //text
	@Column(name="type")
	private Integer type;  //type
	@Column(name="indexOrder")
	private Integer indexOrder;  //indexOrder
	@Column(name="note")
	private String note;  //note
	@Column(name="delStatus")
	private Integer delStatus;  //delStatus
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="createDate")
	private Date createDate;  //createDate
	
	
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

	public String getText(){
		return text;
	}

	public void setText(String text){
		this.text = text;
	}

	public Integer getType(){
		return type;
	}

	public void setType(Integer type){
		this.type = type;
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

	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	
	

	
	
}
