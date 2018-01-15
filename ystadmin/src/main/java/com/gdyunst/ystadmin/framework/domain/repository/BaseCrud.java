package com.gdyunst.ystadmin.framework.domain.repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import com.gdyunst.ystadmin.framework.service.domain.admin.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.gdyunst.ystadmin.application.constant.Constant;
import com.gdyunst.ystadmin.application.utils.EmptyUtil;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;


/**
 * 功能描述:通用crud基类实现 
 * @Author:denghsuai
 * @Date:2017年1月11日 下午4:14:32
 */
public class BaseCrud<T extends IObject> extends IObject implements IBaseCrud<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseCrud.class);
	


	@Override
	public T update(){
		if(this.getOperatorId()!=null){
			User user = new User();
			user.setId(this.getOperatorId());
			String operator = user.doGetRealName();
			this.setOperator(operator);
		}
		this.setOperateDate(new Date());
		T o = null;
		int row = CrudRespository.update(this);
		if(row>0){
			o = (T) CrudRespository.load(this);
			
		}
		
		return o;
	}


	@Override
	public int remove(){
		
		int row = 0;
		Method method = null;
		
		try {
			method = this.getClass().getMethod("setDelStatus",Integer.class);
		} catch (NoSuchMethodException | SecurityException e) {
			row = CrudRespository.remove(this);
			return row;
		}
			
		
		try {
			method.invoke(this, 1);
			Object o = CrudRespository.update(this);
			if(o!=null){
				row = 1;
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			row = 0;
			e.printStackTrace();
		}

		
		return row;
	}


	@Override
	public T load(){
		if(!this.hasId()){
			LOGGER.info("{} do not has id,Load fail!",this.getClass());
			return null;
		}
		T o = (T) CrudRespository.load(this);
		if(o == null) return null;
        Field[] field = o.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
        Class<? extends BaseCrud> className = this.getClass();
        LOGGER.debug("className============="+className);
        try {
            for (int j = 0; j < field.length; j++) { // 遍历所有属性
                 String fieldName = field[j].getName(); // 获取属性的名字
                 String getName = "get"+fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1); // 将属性的首字符大写，构造get方法
                 String setName = "set"+fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1); // 将属性的首字符大写，构造set方法
                 Method getMethod = o.getClass().getMethod(getName);
                 Method setMethod = className.getMethod(setName,field[j].getType());
                 
                 setMethod.invoke(this, getMethod.invoke(o));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
		return o;
	}


	@Override
	public T create(){
		if(this.getOperatorId()!=null){
			User user = new User();
			user.setId(this.getOperatorId());
			String operator = user.doGetRealName();
			this.setOperator(operator);
		}
		if(this.getCreaterId()!=null){
			User user = new User();
			user.setId(this.getOperatorId());
			String creater = user.doGetRealName();
			this.setCreater(creater);
		}
		this.setOperateDate(new Date());
		T object = null;
		if(this.getId()==null || this.getId().equals("") || this.getId().length() < 32){
			UUID id = UUID.randomUUID();
			this.setId(id.toString().replaceAll("-", ""));
		}

		int row = CrudRespository.create(this);
		if(row>0){
			object = (T) CrudRespository.load(this);
			
		}
		return object;
	}


	@Override
	public Result list(int page, int limit)
	{
		Method method = null;

		try 
		{
			method = this.getClass().getMethod("setDelStatus", Integer.class);
			method.invoke(this,Constant.DEL_STATUS_0);
		} 
		catch (Exception e)
		{
			LOGGER.info("类没有setDelStatus方法，此抛异常，可不用理会，继续运行即可。");
		}
		if(page<=0)
		{	
			// 不分页;
			List<Object> oList = CrudRespository.list(this);
			if(oList != null && oList.size() > 0)
			{
				return Result.listSuccessed(oList, (long)oList.size());
			}
			else
			{
				return Result.successed(null, "未查询到数据"); 
			}
		}
		else
		{
			// 分页;
			return CrudRespository.listByPage(this, page, limit);
		}
		
	}


	@Override
	public int creates(List<Object> list,Class<?> clazz){
		int row=0;
		List<T> l=new ArrayList<>();
		if(list!=null&&list.size()>0){
			for (Object o : list) {
				String json = JSON.toJSONString(o);
				T t=(T)JSON.parseObject(json, clazz);
				if(EmptyUtil.isEmpty(t.getId())){
					t.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				}
				l.add(t);
			}
		}
		row = CrudRespository.creates(this,l);
		return row;
	}
	
	@Override
	public int creates(List<T> list){
		int row=0;
		List<T> l=new ArrayList<>();
		if(list!=null&&list.size()>0){
			for (T t : list) {
				if(EmptyUtil.isEmpty(t.getId())){
					t.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				}
				l.add(t);
			}
		}
		row = CrudRespository.creates(this,l);
		return row;
	}


	@Override
	public List<T> list(){
		return (List<T>) CrudRespository.list(this);
	}


	


	@Override
	public Result mlist(Object object, Integer page, Integer limit){
		Method method = null;
		Map map=(Map) object;
		try 
		{
			method = this.getClass().getMethod("setDelStatus", Integer.class);
			method.invoke(this,Constant.DEL_STATUS_0);
			map.put("delStatus", Constant.DEL_STATUS_0);
		} 
		catch (Exception e)
		{
			LOGGER.info("类没有setDelStatus方法，此抛异常，可不用理会，继续运行即可。");
		}
		if(page<=0)
		{	
			// 不分页;
			List<Object> oList = CrudRespository.mlist(map,this);
			if(oList != null && oList.size() > 0)
			{
				return Result.listSuccessed(oList, (long)oList.size());
			}
			else
			{
				return Result.successed(null, "未查询到数据"); 
			}
		}
		else
		{
			// 分页;
			return CrudRespository.mlistByPage(map,this, page, limit);
		}
	}


}
