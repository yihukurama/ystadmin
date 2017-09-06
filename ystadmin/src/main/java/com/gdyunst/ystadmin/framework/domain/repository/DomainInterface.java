package com.gdyunst.ystadmin.framework.domain.repository;

/**
 * 功能描述:所有业务实体实现此接口 
 * @Author:denghsuai
 * @Date:2017年2月6日 下午2:15:04
 */
public interface DomainInterface<T> {
	
	/**
	 * 功能描述:加载与自己相关的业务实体
	 * @return
	 * @Author:dengshuai
	 * @Date:2017年2月6日 下午2:15:35
	 */
	public T init() throws Exception;
}
