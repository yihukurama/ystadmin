package com.gdyunst.ystadmin.framework.domain.repository;

import java.util.List;

import com.gdyunst.ystadmin.application.exception.CrudException;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;


/**
 * 树型结构数据通用业务处理
 * 
 * @author Jieyq
 * @date 2017年1月10日 下午2:48:08
 * @param <T>
 */
public interface ITreeCrud<T extends ITree> extends IBaseCrud<T> {
	
	/**
	 * 功能描述:拖拽树
	 * @return
	 * @Author:Jieyq
	 * @Date:2017年1月10日 下午2:52:26
	 */
	public Result drag() throws InstantiationException, IllegalAccessException,CrudException;
	
	/**
	 * 功能描述:获取树型列表
	 * @param sqlSession
	 * @return
	 * @Author:Jieyq
	 * @Date:2017年1月10日 下午2:52:45
	 */
	public List<Object> treeList() throws 
		InstantiationException, IllegalAccessException, ClassNotFoundException, CrudException;
}
