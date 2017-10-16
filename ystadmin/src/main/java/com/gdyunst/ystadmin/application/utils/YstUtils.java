package com.gdyunst.ystadmin.application.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.gdyunst.ystadmin.framework.domain.repository.ITree;

/**
 * 功能描述: 数据实体和业务实体之间的转化工具
 * @Author:denghsuai
 * @Date:2017年10月16日 上午11:01:25
 */
public class YstUtils {

	/**
	 * 功能描述:把数据实体类转化为业务实体类
	 * 推荐使用transferDomain2Entity(S domain,Class<?> clazz)
	 * @param entity  数据实体对象
	 * @param domain  业务实体对象
	 * @return 数据实体转化后的业务实体
	 * @Author:dengshuai
	 * @Date:2017年1月20日 下午2:09:15
	 */
	@Deprecated
	public static <B, S extends B> S transferEntity2Domain(B entity, S domain) {

		if (entity == null || domain == null) {
			return null;
		}
		Class<?> clazz;
		try {
			clazz = Class.forName(domain.getClass().getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		String jsonString = JSON.toJSONString(entity);
		domain = (S) JSON.parseObject(jsonString, clazz);

		return domain;

	}

	/**
	 * 功能描述:把业务实体转化为数据实体
	 * 推荐使用 transferEntity2Domain(B entity, Class<?> clazz)
	 * @param entity 数据实体对象
	 * @param domain 业务实体对象
	 * @return 转化后的数据实体。若参数有1个为null，返回null
	 * @Author:dengshuai
	 * @Date:2017年1月20日 下午2:11:50
	 */
	@Deprecated
	public static <B, S extends B> B transferDomain2Entity(B entity, S domain) {

		if (entity == null || domain == null) {
			return null;
		}
		Class<?> clazz;
		try {
			clazz = Class.forName(entity.getClass().getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		String jsonString = JSON.toJSONString(domain);
		entity = (B) JSON.parseObject(jsonString, clazz);

		return entity;

	}
	
	
	/**
	 * 功能描述:把数据实体类转化为业务实体类
	 * @param entity  数据实体对象
	 * @param clazz  业务实体对象
	 * @return 数据实体转化后的业务实体
	 * @Author:dengshuai
	 * @Date:2017年1月20日 下午2:09:15
	 */
	public static <B, S extends B> S transferEntity2Domain(B entity, Class<S> clazz) {

		if (entity == null) {
			return null;
		}
		String jsonString = JSON.toJSONString(entity);
		S domain =  JSON.parseObject(jsonString, clazz);

		return domain;

	}
	
	/**
	 * 功能描述:把数据实体列表转化为业务实体列表
	 * @param entityList
	 * @param clazz
	 * @return 业务实体列表，不会为null
	 * @Author:dengshuai
	 * @Date:2017年2月4日 上午10:42:40
	 */
	public static <B, S extends B> List<S> transferEntityList2DomainList(List<B> entityList, Class<S> clazz) {

		List<S> sList = new ArrayList<>();
		if (entityList == null) {
			return sList;
		}
		List<S> list = JSON.parseArray(JSON.toJSONString(entityList),clazz);
		if (list == null){
			return sList;
		}
		return list;
	}
	
	/**
	 * 功能描述:把业务实体转化为数据实体
	 * @param clazz 
	 * @param domain
	 * @return
	 * @Author:dengshuai
	 * @Date:2017年1月20日 下午2:16:42
	 */
	public static <B, S extends B> B transferDomain2Entity(S domain,Class<?> clazz) {

		if (domain == null) {
			return null;
		}
		
		String jsonString = JSON.toJSONString(domain);
		B entity = (B) JSON.parseObject(jsonString, clazz);

		return entity;

	}
	
	/**
	 * 功能描述:把业务实体列表转化为数据实体列表
	 * @param domainList
	 * @param clazz
	 * @return
	 * @Author:dengshuai
	 * @Date:2017年2月4日 上午10:48:00
	 */
	public static <B, S extends B> List<B> transferDomainList2EntityList(List<S> domainList,Class<?> clazz) {

		List<B> bList = new ArrayList<>();
		if (domainList == null) {
			return bList;
		}
		for (B b : domainList) {
			String jsonString = JSON.toJSONString(b);
			B entity = (B) JSON.parseObject(jsonString, clazz);
			bList.add(entity);
		}
		

		return bList;

	}
	
	/**
	 * 功能描述:
	 * @param treeList   
	 * @param treeIdList 树列表id集合
	 * @return
	 * @Author:dengshuai
	 * @Date:2017年1月18日 下午4:13:12
	 */
	public static List<Object> setITreeList(List<Object> treeList,List<String> treeIdList){
		List<Object> rlist=new ArrayList<>();
		if(treeList!=null&&treeList.size()>0&&treeIdList!=null&&treeIdList.size()>0){
			for (Object tree : treeList) {
				//循环遍历所有有权限的菜单Id
				ITree iTree = (ITree) tree;
				for (String treeId : treeIdList) {
					if(iTree.getId()!=null&&iTree.getId().equals(treeId)){
						if(iTree.getChildren()!=null&&iTree.getChildren().size()>0){
							//递归调用
							iTree.setChildren(setITreeList(iTree.getChildren(),treeIdList));
						}
						rlist.add(iTree);
						break;
					}
				}
			}
		}
		return rlist;
	}
}
