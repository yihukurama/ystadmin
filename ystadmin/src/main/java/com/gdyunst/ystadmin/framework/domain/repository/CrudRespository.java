package com.gdyunst.ystadmin.framework.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gdyunst.ystadmin.application.component.SpringBeanTools;
import com.gdyunst.ystadmin.application.exception.CrudException;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class CrudRespository {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrudRespository.class);
	
	private static SqlSession getSqlSession(){
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringBeanTools.getBean(SqlSessionFactory.class);
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	} 
	
	public static Result listByPage(IObject condition, int page, int limit) throws CrudException
	{
		
		Result result = new Result();

		PageHelper.startPage(page, limit, true, true);
		
		String domainName = condition.getClass().getSimpleName();
		if(domainName.endsWith("Entity")){
			domainName = domainName.replace("Entity", "");
		}
		String mapperId = "list" + domainName;
		try(SqlSession sqlSession = getSqlSession()){
			if (sqlSession != null)
			{
				List<Object> resultList = sqlSession.selectList(mapperId, condition);
				sqlSession.close();
				PageInfo<Object> pageInfo = new PageInfo<Object>(resultList);
				result = Result.listSuccessed(resultList, pageInfo.getTotal());
			
				return result;
			} 
			else 
			{
				LOGGER.info("未获得sqlSession");
			}
		}catch (Exception e) {
			throw new CrudException(e.getMessage());
		}
		

		return result;
	}

	public static <T> List<T> list(T condition) throws CrudException{

		String domainName = condition.getClass().getSimpleName();
		if(domainName.endsWith("Entity")){
			domainName = domainName.replace("Entity", "");
		}
		String mapperId = "list" + domainName;
		try(SqlSession sqlSession = getSqlSession()){
			if (sqlSession != null) {
				List<T> resultList = sqlSession.selectList(mapperId, condition);
				sqlSession.close();
				return resultList;
			} else {
				LOGGER.info("未获得sqlSession");
			}
		}catch (Exception e) {
			throw new CrudException(e.getMessage());
		}

		return new ArrayList<>();
	}

	public static int create(IObject t) throws CrudException{
		
		int row = 0;
		String domainName = t.getClass().getSimpleName();
		LOGGER.info("domainName is ==========>{}",domainName);
		if(domainName.endsWith("Entity")){
			domainName = domainName.replace("Entity", "");
			LOGGER.info("replaceDomainName is ==========>{}",domainName);
		}
		LOGGER.info("lastDomainName is ==========>{}",domainName);
		String mapperId = "create" + domainName;
		try(SqlSession sqlSession = getSqlSession()){
			if (sqlSession != null) {
				LOGGER.info("获得sqlSession");
				row = sqlSession.insert(mapperId, t);
				sqlSession.close();
			} else {
				LOGGER.info("未获得sqlSession");
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new CrudException(e.getMessage());
		}
		return row;
	}

	public static int remove(IObject t) throws CrudException{
		int row = 0;
		String domainName = t.getClass().getSimpleName();
		if(domainName.endsWith("Entity")){
			domainName = domainName.replace("Entity", "");
		}
		String mapperId = "remove" + domainName;
		try(SqlSession sqlSession = getSqlSession()){
			if (sqlSession != null) {
				row = sqlSession.delete(mapperId, t);
				sqlSession.close();
			} else {
				LOGGER.info("未获得sqlSession");
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new CrudException(e.getMessage());
		}
		return row;
	}

	public static int update(IObject t) throws CrudException{
		int row = 0;
		String domainName = t.getClass().getSimpleName();
		if(domainName.endsWith("Entity")){
			domainName = domainName.replace("Entity", "");
		}
		String mapperId = "update" + domainName;
		try(SqlSession sqlSession = getSqlSession()){
			if (sqlSession != null) {
				
				row = sqlSession.update(mapperId, t);
				sqlSession.close();
			} else {
				LOGGER.info("未获得sqlSession");
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new CrudException(e.getMessage());
		}
		return row;
	}

	public static <T> T load(T t) throws CrudException{
		T object = null;
		String domainName = t.getClass().getSimpleName();
		if(domainName.endsWith("Entity")){
			domainName = domainName.replace("Entity", "");
		}
		String mapperId = "load" + domainName;
		try(SqlSession sqlSession = getSqlSession()){
			if (sqlSession != null) {
				object = sqlSession.selectOne(mapperId, t);
				sqlSession.close();
			} else {
				LOGGER.info("未获得sqlSession");
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new CrudException(e.getMessage());
		}
		return object;
	}

	public static int updateTree(IObject t) throws CrudException{
		int row = 0;
		String domainName = t.getClass().getSimpleName();
		if(domainName.endsWith("Entity")){
			domainName = domainName.replace("Entity", "");
		}
		String mapperId = "updateTree" + domainName;
		try(SqlSession sqlSession = getSqlSession()){
			if (sqlSession != null) {
				row = sqlSession.update(mapperId, t);
				sqlSession.close();
			} else {
				LOGGER.info("未获得sqlSession");
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new CrudException(e.getMessage());
		}
		return row;
	}
	
	public static int removeTree(IObject t) throws CrudException{
		int row = 0;
		String domainName = t.getClass().getSimpleName();
		if(domainName.endsWith("Entity")){
			domainName = domainName.replace("Entity", "");
		}
		String mapperId = "removeTree" + domainName;
		try(SqlSession sqlSession = getSqlSession()){
			if (sqlSession != null) {
				row = sqlSession.delete(mapperId, t);
				sqlSession.close();
			} else {
				LOGGER.info("未获得sqlSession");
			}
		}catch (Exception e) {
			throw new CrudException(e.getMessage());
		}
		return row;
	}

	public static int creates(IObject t,Object list) throws CrudException{
		int row = 0;
		String domainName = t.getClass().getSimpleName();
		if(domainName.endsWith("Entity")){
			domainName = domainName.replace("Entity", "");
		}
		String mapperId = "insertBatch" + domainName;
		try(SqlSession sqlSession = getSqlSession()){
			if (sqlSession != null) {
				row = sqlSession.insert(mapperId, list);
				sqlSession.close();
			} else {
				LOGGER.info("未获得sqlSession");
			}
		}catch (Exception e) {
			throw new CrudException(e.getMessage());
		}
		
		return row;
	}

	public static <T> List<T> mlist(Object object,T baseCrud) throws CrudException {
		String domainName = baseCrud.getClass().getSimpleName();
		if(domainName.endsWith("Entity")){
			domainName = domainName.replace("Entity", "");
		}
		String mapperId = "mlist" + domainName;
		try(SqlSession sqlSession = getSqlSession()){
			if (sqlSession != null) {
				List<T> resultList = sqlSession.selectList(mapperId, object);
				sqlSession.close();
			
				return resultList;
			} else {
				LOGGER.info("未获得sqlSession");
			}
		}catch (Exception e) {
			throw new CrudException(e.getMessage());
		}
		

		return new ArrayList<>();
	}

	public static Result mlistByPage(Object object, IObject condition, Integer page, Integer limit) throws CrudException {
		Result result = new Result();

		PageHelper.startPage(page, limit, true, true);
		
		String domainName = condition.getClass().getSimpleName();
		if(domainName.endsWith("Entity")){
			domainName = domainName.replace("Entity", "");
		}
		String mapperId = "mlist" + domainName;
		try(SqlSession sqlSession = getSqlSession()){
			if (sqlSession != null)
			{
				List<Object> resultList = sqlSession.selectList(mapperId, object);
				sqlSession.close();
				PageInfo<Object> pageInfo = new PageInfo<Object>(resultList);
				result = Result.listSuccessed(resultList, pageInfo.getTotal());
			
				return result;
			} 
			else 
			{
				LOGGER.info("未获得sqlSession");
			}
		}catch (Exception e) {
			throw new CrudException(e.getMessage());
		}

		return result;
	}

}
