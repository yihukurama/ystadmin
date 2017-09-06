package com.gdyunst.ystadmin.framework.domain.repository;

import java.util.List;

public abstract class ITree extends IObject {
	
	protected String id;
	protected String parentId;
	protected String text;
	protected String path;
	protected Integer indexOrder;
	protected List<Object> children;
	protected Boolean leaf;
	protected Boolean asyn;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	public Integer getIndexOrder() {
		return indexOrder;
	}

	public void setIndexOrder(Integer indexOrder) {
		this.indexOrder = indexOrder;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public List<Object> getChildren() {
		return children; 
	}

	public void setChildren(List<Object> children) {
		this.children = children;
	}

	public Boolean getAsyn() {
		return asyn;
	}

	public void setAsyn(Boolean asyn) {
		this.asyn = asyn;
	}
	
	
	
}
