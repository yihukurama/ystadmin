package com.gdyunst.ystadmin.framework.service.domain.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gdyunst.ystadmin.application.exception.TipsException;
import com.gdyunst.ystadmin.application.utils.EmptyUtil;
import com.gdyunst.ystadmin.application.utils.LogUtil;
import com.gdyunst.ystadmin.application.utils.YstUtils;
import com.gdyunst.ystadmin.framework.domain.entity.admin.EmpservbranchEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.ServicebranchEntity;
import com.gdyunst.ystadmin.framework.domain.repository.IObject;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;

import java.util.List;

public class Empservbranch extends EmpservbranchEntity {

    private String orgName;
    private String departmentName;
    private String branchName;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    private ServicebranchEntity servicebranchEntity;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public ServicebranchEntity getServicebranchEntity() {
        return servicebranchEntity;
    }

    public void setServicebranchEntity(ServicebranchEntity servicebranchEntity) {
        this.servicebranchEntity = servicebranchEntity;
    }

    @Override
    public int creates(List<EmpservbranchEntity> list) {
        if(!EmptyUtil.isEmpty(list)){
            //绑定员工和服务点需要先把原来的删除
            LogUtil.DebugLog(this,"删除原来的员工服务点绑定");
            EmpservbranchEntity empservbranchEntity = new EmpservbranchEntity();
            empservbranchEntity.setEmployeeId(list.get(0).getEmployeeId());
            empservbranchEntity.remove();
        }


        return super.creates(list);
    }

    @Override
    public int creates(List<Object> list, Class<?> clazz) throws TipsException {

        if(!EmptyUtil.isEmpty(list)){
            //绑定员工和服务点需要先把原来的删除
            LogUtil.DebugLog(this,"删除原来的员工服务点绑定");
            String json = JSON.toJSONString(list.get(0));
            EmpservbranchEntity empservbranch = JSON.parseObject(json,EmpservbranchEntity.class);
            EmpservbranchEntity empservbranchEntity = new EmpservbranchEntity();
            empservbranchEntity.setEmployeeId(empservbranch.getEmployeeId());
            empservbranchEntity.remove();
        }


        return super.creates(list,clazz);
    }

    public EmpservbranchEntity doGetServiceBranch() {
        String branchId = this.getServiceBranchId();
        ServicebranchEntity servicebranchEntity = new Servicebranch();
        servicebranchEntity.setId(branchId);
        servicebranchEntity.load();
        this.setServicebranchEntity(servicebranchEntity);
        this.setOrgName(servicebranchEntity.getOrgName());
        this.setDepartmentName(servicebranchEntity.getDepartmentName());
        this.setBranchName(servicebranchEntity.getText());
        return this;
    }

    @Override
    public IObject doGetRelationData() {
        super.doGetRelationData();
        doGetServiceBranch();
        return this;
    }

    @Override
    public Result list(int page, int limit) {

        Result result = super.list(page, limit);
        List<EmpservbranchEntity> empservbranchEntities = (List<EmpservbranchEntity>) result.getData();
        for (int i = 0; i < empservbranchEntities.size(); i++) {
            Empservbranch empservbranch = YstUtils.transferEntity2Domain(empservbranchEntities.get(i),Empservbranch.class);
            empservbranch.doGetRelationData();
            empservbranchEntities.set(i,empservbranch);
        }
        return Result.successed(empservbranchEntities);
    }
}
