package com.gdyunst.ystadmin.framework.domain.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class IObject implements DomainInterface<IObject>{
	public static Logger LOGGER = LoggerFactory.getLogger(IObject.class);
	protected String id;
	
	protected Long total;     		// 总记录数;

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
