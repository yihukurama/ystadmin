package com.gdyunst.ystadmin.framework.domain.repository;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.gdyunst.ystadmin.application.exception.TipsException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gdyunst.ystadmin.application.exception.BusinessRuntimeException;
import com.gdyunst.ystadmin.application.utils.EmptyUtil;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;



public class TreeCrud<T extends ITree> extends ITree implements ITreeCrud<T> {

	@Autowired
	protected SqlSessionFactory sqlSessionFactory;


	@Override
	public T update(){
		
		int row = CrudRespository.update(this);
		if(row>0){
			T data = (T) CrudRespository.load(this);
			return data;
			
		}
		return null;
	}


	@Override
	@Transactional
	public int remove(){
		int row=0;
		List<Object> itlist= CrudRespository.list(this);
		if(itlist!=null&&itlist.size()>0){
			for (Object object : itlist) {
				ITree it=(ITree)object;
				if(it!=null&&!EmptyUtil.isEmpty(it.getPath())){
					this.setPath(it.getPath());
					row+=CrudRespository.removeTree(this);
				}
			}
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
        Class<? extends TreeCrud> className = this.getClass();
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
	public T create() {
		Object result = null;
		UUID id = UUID.randomUUID();
		this.setId(id.toString().replaceAll("-", ""));
		String path="/root/";
		if(!"root".equals(this.getParentId())){
			if(EmptyUtil.isEmpty(this.getParentId())){
				throw new BusinessRuntimeException("父类Id不能为空！");
			}
			T t=null;
			try {
				t = (T) this.getClass().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				throw new BusinessRuntimeException("创建有异常");
			}
			t.setId(this.getParentId());
			ITreeCrud<ITree> treeCurd = (ITreeCrud<ITree>)t;
			t=(T) CrudRespository.load(t);
			if(t!=null&&!EmptyUtil.isEmpty(t.getPath())){
				path=t.getPath();
			}else{
				throw new BusinessRuntimeException("没有对应id为"+this.getParentId()+"的父类！");
			}
		}
		path=path+this.getId()+"/";
		this.setPath(path);
		int row = CrudRespository.create(this);
		if(row>0){
			T data = (T) CrudRespository.load(this);
			return data;
		}
		return null;
	}

	@Override
	public Result list(int pageNum, int pageSize) {
		Object o = CrudRespository.list(this);
		if(o!=null){
			return Result.successed(o, "查询成功");
		}
		return Result.failed("查询失败");
	}


	@Override
	@Transactional
	public Result drag() throws InstantiationException, IllegalAccessException{
		int row=0;
		if(EmptyUtil.isEmpty(this.getParentId())||EmptyUtil.isEmpty(this.getId())){
			return Result.failed("父类Id不能为空！");
		}
		String path="/root/";
		if(!"root".equals(this.getParentId())){
			T t=(T) this.getClass().newInstance();
			t.setId(this.getParentId());
			ITreeCrud<ITree> treeCurd = (ITreeCrud<ITree>)t;
			t=(T) CrudRespository.load(t);
			if(t!=null&&!EmptyUtil.isEmpty(t.getPath())){
				path=t.getPath();
			}else{
				return Result.failed("没有对应id为"+this.getParentId()+"的父类！");
			}
		}
		path=path+this.getId()+"/";

		this.setPath(path);
		row+=CrudRespository.update(this);
		row+=CrudRespository.updateTree(this);
		if(row>0){
			return Result.successed(null);
		}
		return Result.failed("没有修改，拖拽失败");
	}
	
	public Result treeList(int pageNum, int pageSize){
		Object o = CrudRespository.list(this);
		if(o!=null){
			return Result.successed(o, "查询成功");
		}
		return Result.failed("查询失败");
	}


	@Override
	public List<Object> treeList() throws InstantiationException, IllegalAccessException, TipsException {
		List<Object> list = CrudRespository.list(this);
		Boolean asyn=false;
		if(this.getAsyn()!=null){
			asyn=this.getAsyn();
		}
		if(list!=null&&list.size()>0)
		{
			for(Object o : list)
			{
				recursionTreeList((T)o,asyn);
			}
		}
		return list;
	}

	/**
	 * 功能描述:递归遍历菜单对象,生成树形结构菜单;
	 * @param menu Menu对象
	 * @return 树形结构菜单
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws Exception 
	 * @Author:liujun
	 * @Date:2017年1月5日 下午2:19:44
	 */
	private T recursionTreeList(T t,Boolean asyn) throws InstantiationException, IllegalAccessException, TipsException {
		T tt=(T) t.getClass().newInstance();
		tt.setParentId(t.getId());
		ITreeCrud<ITree> treeCurd = (ITreeCrud<ITree>)tt;
		List<Object> list=(List<Object>) treeCurd.list(0,0).getData();
		if(list!=null&&list.size()>0)
		{
			t.setLeaf(false);
			if(!asyn){
				t.setChildren(list);
				for(Object o : list)
				{
					recursionTreeList((T)o,asyn);
				}
			}
		}
		else
		{
			t.setLeaf(true);
			t.setChildren(new ArrayList<>());
		}
		return t;
	}


	@Override
	public int creates(List<Object> list,Class<?> clazz){
		int row=0;
		row=CrudRespository.creates(this,list);
		return row;
	}
	
	@Override
	public int creates(List<T> list){
		int row=0;
		row=CrudRespository.creates(this,list);
		return row;
	}


	@Override
	public List<T> list(){
		return (List<T>) CrudRespository.list(this);
	}



	@Override
	public Result mlist(Object object, Integer page, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
