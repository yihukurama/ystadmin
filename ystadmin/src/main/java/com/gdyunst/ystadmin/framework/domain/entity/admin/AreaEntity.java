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
	private String id;   //序列号
	@Column(name="parentId")
	private String parentId;  //父类区域id,root代表一级行政区划,默认为root;
	@Column(name="path")
	private String path;  //分层路径
	@Column(name="text")
	private String text;  //区域名称
	@Column(name="type")
	private String type;  //区域类型(1省、直辖市、自治区、特别行政区;2市;3区、县,4,乡镇)
	@Column(name="indexOrder")
	private String indexOrder;  //排序字段
	@Column(name="note")
	private String note;  //备注信息
	@Column(name="delStatus")
	private String delStatus;  //删除状态,0未删除,1删除
	@Column(name="createDate")
	private String createDate;  //创建时间
	
	
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

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getIndexOrder(){
		return indexOrder;
	}

	public void setIndexOrder(String indexOrder){
		this.indexOrder = indexOrder;
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

	public String getCreateDate(){
		return createDate;
	}

	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	
	

	
	
}
