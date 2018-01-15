package com.gdyunst.ystadmin.framework.web.restful;

import com.alibaba.fastjson.JSON;
import com.gdyunst.ystadmin.framework.domain.repository.IBaseCrud;
import com.gdyunst.ystadmin.framework.domain.repository.IObject;
import com.gdyunst.ystadmin.framework.domain.repository.ITree;
import com.gdyunst.ystadmin.framework.domain.repository.ITreeCrud;
import com.gdyunst.ystadmin.framework.service.ViewDataServiceImpl;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Request;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.ViewDto;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

public class CommController {
    private Logger LOGGER = LoggerFactory.getLogger(CommController.class);
    
    @Autowired
    ViewDataServiceImpl viewDataService;
    
	protected String packageD = "";

	@ApiOperation(httpMethod = "POST", value = "通用插入1条数据", notes = "通用插入1条数据")
	@RequestMapping(value = "/{domain}/create", method = RequestMethod.POST)
	public Result createOne(@RequestBody Request request, @PathVariable String domain) throws Exception {

		
		String classPackage = packageD + domain;

		Class<?> clazz = Class.forName(classPackage);
		String json = JSON.toJSONString(request.getData());
		Object tt = JSON.parseObject(json, clazz);
		IBaseCrud<IObject> baseCurd = (IBaseCrud<IObject>) tt;
		IObject object=baseCurd.create();
		if(object == null){
			return Result.failed(domain+"create接口错误");
		}else{
			return Result.successed(object);	
		}
		
	}

	@ApiOperation(httpMethod = "POST", value = "通用删除1条数据", notes = "通用删除1条数据")
	@RequestMapping(value = "/{domain}/remove", method = RequestMethod.POST)
	public Result removeOne(@RequestBody Request request, @PathVariable String domain) throws Exception {
		LOGGER.debug(domain+"删除接口");
		String classPackage = packageD + domain;
		int row = 0;
		Class<?> clazz = Class.forName(classPackage);
		String json = JSON.toJSONString(request.getData());
		Object tt = JSON.parseObject(json, clazz);
		IBaseCrud<IObject> baseCurd = (IBaseCrud<IObject>) tt;
		row = baseCurd.remove();
		
		if(row > 0){
			return Result.successed(row);	
		}else{
			return Result.failed(domain+"remove接口错误");
		}
		


	}

	@ApiOperation(httpMethod = "POST", value = "通用更新1条数据", notes = "通用更新1条数据")
	@RequestMapping(value = "/{domain}/update", method = RequestMethod.POST)
	public Result updateOne(@RequestBody Request request, @PathVariable String domain) throws Exception {
		
		String classPackage = packageD + domain;
		Class<?> clazz = Class.forName(classPackage);
		String json = JSON.toJSONString(request.getData());
		Object tt = JSON.parseObject(json, clazz);
		IBaseCrud<IObject> baseCurd = (IBaseCrud<IObject>) tt;
		Object object = baseCurd.update();
		if(object == null){
			return Result.failed(domain+"update接口错误");
		}else{
			return Result.successed(object);	
		}

	}

	@ApiOperation(httpMethod = "POST", value = "通过id获取1条数据", notes = "通过id获取1条数据")
	@RequestMapping(value = "/{domain}/load", method = RequestMethod.POST)
	public Result loadOne(@RequestBody Request request, @PathVariable String domain) throws Exception {
		
		String classPackage = packageD + domain;
		IObject o = null;
		Class<?> clazz = Class.forName(classPackage);
		String json = JSON.toJSONString(request.getData());
		Object tt = JSON.parseObject(json, clazz);
		if(json.contains("\"parentId\"")){
			ITreeCrud<ITree> treeCurd = (ITreeCrud<ITree>) tt;
			o = treeCurd.load();
		}else{
			IBaseCrud<IObject> baseCurd = (IBaseCrud<IObject>) tt;
			o = baseCurd.load();
		}
		if(o != null)
		{
			return Result.successed(o, "查询成功", 1L);
		}
		else
		{
			return Result.successed(o, "未查询到数据", 0L);
		}
	}

	@ApiOperation(httpMethod = "POST", value = "条件获取数据,page=0不分页", notes = "条件获取数据,page=0不分页")
	@RequestMapping(value = "/{domain}/list", method = RequestMethod.POST)
	public Object list(@RequestBody Request request, @PathVariable String domain) throws Exception {
		
		String classPackage = packageD + domain;
		Class<?> clazz = Class.forName(classPackage);
		String json = JSON.toJSONString(request.getData());

		Object tt = JSON.parseObject(json, clazz);
		IBaseCrud<IObject> baseCurd = (IBaseCrud<IObject>) tt;

		return baseCurd.list(request.getPage(), request.getLimit());
		
	}
	
	@ApiOperation(httpMethod = "POST", value = "条件获取数据,page=0不分页", notes = "条件获取数据,page=0不分页")
	@RequestMapping(value = "/{domain}/mlist", method = RequestMethod.POST)
	public Object mlist(@RequestBody Request request, @PathVariable String domain) throws Exception {
		
		String classPackage = packageD + domain;
		Class<?> clazz = Class.forName(classPackage);
		String json = JSON.toJSONString(request.getData());
		Object tt = JSON.parseObject(json, clazz);
		IBaseCrud<IObject> baseCurd = (IBaseCrud<IObject>) tt;
		
		return baseCurd.mlist(request.getData(),request.getPage(), request.getLimit());
		
	}

	/**
	 * 功能描述:获取树型列表
	 * 
	 * @param request
	 * @param domain
	 * @return
	 * @Author:Jieyq
	 * @Date:2017年1月11日 上午9:15:22
	 */
	@ApiOperation(httpMethod = "POST", value = "获取树型列表", notes = "获取树型列表")
	@ApiImplicitParams({})
	@RequestMapping(value = "/{domain}/tree", method = RequestMethod.POST)
	public Object treeList(@RequestBody Request<Object> request, @PathVariable String domain) {
		String classPackage = packageD + domain;
		List<Object> list =new ArrayList<>();
		try {
			Class<?> clazz = Class.forName(classPackage);
			String json = JSON.toJSONString(request.getData());
			Object tt = JSON.parseObject(json, clazz);
			System.out.println("className" + clazz.getName());
			ITreeCrud<ITree> treeCurd = (ITreeCrud<ITree>) tt;
			list = treeCurd.treeList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	/**
	 * 功能描述:拖拽整棵树
	 * 
	 * @param request
	 * @param domain
	 * @return
	 * @Author:Jieyq
	 * @Date:2017年1月11日 上午9:15:48
	 */
	@ApiOperation(httpMethod = "POST", value = "拖拽整棵树", notes = "拖拽整棵树")
	@RequestMapping(value = "/{domain}/drag", method = RequestMethod.POST)
	public Result drag(@RequestBody Request request, @PathVariable String domain) {
		Result result = new Result();
		String classPackage = packageD + domain;
//		int row = 0;
		try {
			Class<?> clazz = Class.forName(classPackage);
			String json = JSON.toJSONString(request.getData());
			Object tt = JSON.parseObject(json, clazz);
			System.out.println("className" + clazz.getName());
			ITreeCrud<ITree> treeCurd = (ITreeCrud<ITree>) tt;
			result = (Result) treeCurd.drag();
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("拖拽失败,有异常情况，请联系管理员");
		}

		return result;

	}
	
	/**
	 * 
	 * 功能描述:
	 * @param request
	 * @param domain
	 * @return
	 * @throws Exception
	 * @Author:Jieyq
	 * @Date:2017年2月16日 上午11:35:43
	 */
	@ApiOperation(httpMethod = "POST", value = "通用插入1条数据", notes = "通用插入1条数据")
	@RequestMapping(value = "/{domain}/creates", method = RequestMethod.POST)
	public Result creates(@RequestBody Request<List> request, @PathVariable String domain) throws Exception {

		List<Object> list=(List<Object>) request.getData();
		if(list==null||list.size()<=0){
			return Result.failed("插入的list长度不能为null或0");
		}
		String classPackage = packageD + domain;

		Class<?> clazz = Class.forName(classPackage);
		String json = JSON.toJSONString(list.get(0));
		Object tt = JSON.parseObject(json, clazz);
		IBaseCrud<IObject> baseCurd = (IBaseCrud<IObject>) tt;
		int row=baseCurd.creates(list,clazz);
		if(row<=0){
			return Result.failed(domain+"creates接口错误");
		}else{
			return Result.successed(row);	
		}
		
	}
	
	
	/**
     * 功能描述:前端直接查询相应的数据
     * 
     * @param request
     * @return
     * @throws Exception
     * @Author:dengshuai
     * @Date:2017年3月22日 下午4:26:51
     */
    @ApiOperation(httpMethod = "POST", value = "前端直接查询相应的数据", notes = "前端直接查询相应的数据")
    @RequestMapping(value = "archView", method = RequestMethod.POST)
    public Result searchView(@RequestBody Request<ViewDto> request) throws Exception {
        if (request.getData().getParams() == null || request.getData().getStatment() == null) {
            return Result.failed("Parameter 或 Statment不能为null");
        }
        return viewDataService.doView(request.getData().getStatment(), request.getData().getParams(),request.getPage(),request.getLimit());
    }
	
	
}
