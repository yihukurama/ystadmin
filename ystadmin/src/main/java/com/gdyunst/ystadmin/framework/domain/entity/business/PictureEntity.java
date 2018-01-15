package com.gdyunst.ystadmin.framework.domain.entity.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gdyunst.ystadmin.framework.domain.repository.BaseCrud;

/**
 * 功能描述:图片表
 */
@Table(name="business_picture")
public class PictureEntity extends BaseCrud<PictureEntity>
{
	@Id
	private String id;   //序列号
	@Column(name="relateId")
	private String relateId;  //图片所关联的id
	@Column(name="type")
	private Integer type;  //图片类型 1普通类型
	@Column(name="filePath")
	private String filePath;  //图片路径
	@Column(name="picIndex")
	private Integer picIndex;  //图片序号
	@Column(name="delStatus")
	private Integer delStatus;  //删除状态,0未删除,1删除
	@Column(name="origin")
	private Integer origin;  //图片来源：1后台
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")@Column(name="createDate")
	private Date createDate;  //创建时间
	@Column(name="relateType")
	private Integer relateType;  //关联类型
	
	
	//get  set 方法
	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getRelateId(){
		return relateId;
	}

	public void setRelateId(String relateId){
		this.relateId = relateId;
	}

	public Integer getType(){
		return type;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public String getFilePath(){
		return filePath;
	}

	public void setFilePath(String filePath){
		this.filePath = filePath;
	}

	public Integer getPicIndex(){
		return picIndex;
	}

	public void setPicIndex(Integer picIndex){
		this.picIndex = picIndex;
	}

	public Integer getDelStatus(){
		return delStatus;
	}

	public void setDelStatus(Integer delStatus){
		this.delStatus = delStatus;
	}

	public Integer getOrigin(){
		return origin;
	}

	public void setOrigin(Integer origin){
		this.origin = origin;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public Integer getRelateType(){
		return relateType;
	}

	public void setRelateType(Integer relateType){
		this.relateType = relateType;
	}

	
	

	
	
}
