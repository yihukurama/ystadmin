package com.gdyunst.ystadmin.framework.domain.repository;

import com.gdyunst.ystadmin.application.exception.TipsException;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;

import java.util.List;

/**
 * 功能描述:通用curd接口 
 * @Author:denghsuai
 * @Date:2017年1月11日 下午4:14:05
 */
public interface IBaseCrud<T extends IObject> {
	
	/**
	 * 功能描述:往数据库中插入数据对象，成功返回插入后的对象,失败返回null 
	 * @throws TipsException
	 * @Author:denghsuai
	 * @Date:2017年1月14日 下午2:39:35
	 */
	public T create() throws TipsException;
	
	/**
	 * 功能描述:更新数据库中的数据对象
	 * 成功返回更新后的对象
	 * @Author:denghsuai
	 * @Date:2017年1月14日 下午2:40:04
	 */
	public T update() throws TipsException;
	
	/**
	 * 功能描述:删除数据库中的数据对象，成功返回删除条数，失败返回0 
	 * @Author:denghsuai
	 * @Date:2017年1月14日 下午2:41:24
	 */
	public int remove() throws TipsException;
	
	/**
	 * 功能描述:根据id加载数据对象
	 * 成功返回数据对象
	 * @Author:denghsuai
	 * @Date:2017年1月14日 下午2:42:01
	 */
	public T load() throws TipsException;
	
	/**
	 * 功能描述: 条件查询数据对象列表,返回包装类型
	 * pageNum为0代表不分页
	 * @Author:denghsuai
	 * @Date:2017年1月14日 下午2:42:30
	 */
	public Result list(int pageNum, int pageSize) throws TipsException;

	/**
	 * 功能描述:条件查询对象列表,返回非包装类型
	 * @Author:denghsuai
	 * @Date:2017年1月16日 下午3:49:48
	 */
	public List<T> list() throws TipsException;

	/**
	 * 功能描述: 批量插入数据，返回成功条数
	 * @Author:denghsuai
	 * @Date:2017年1月14日 下午2:45:24
	 */
	public int creates(List<Object> T, Class<?> clazz) throws TipsException;
	/**
	 * 功能描述: 批量插入数据，返回成功条数
	 * @Author:denghsuai
	 * @Date:2017年1月14日 下午2:45:24
	 */
	public int creates(List<T> T) throws TipsException;

	/**
	 * 
	 * 功能描述:
	 * @param object
	 * @param page
	 * @param limit
	 * @return
	 * @Author:Jieyq
	 * @Date:2017年3月21日 上午10:32:25
	 */
	public Result mlist(Object object, Integer page, Integer limit) throws TipsException;


}
