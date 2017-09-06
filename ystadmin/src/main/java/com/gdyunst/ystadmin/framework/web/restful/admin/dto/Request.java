package com.gdyunst.ystadmin.framework.web.restful.admin.dto;

public class Request<T> {
	
	private String uid;      // 用户id;
	private Integer page;
	private Integer limit;
	private T data;        // 请求数据;
	public String getUid() {
		return uid;
	}
	
	
	public Integer getPage() {
		return page;
	}


	public void setPage(Integer page) {
		this.page = page;
	}


	public Integer getLimit() {
		return limit;
	}


	public void setLimit(Integer limit) {
		this.limit = limit;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}
	


	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}

}