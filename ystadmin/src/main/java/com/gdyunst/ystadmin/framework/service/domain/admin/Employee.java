package com.gdyunst.ystadmin.framework.service.domain.admin;

import com.gdyunst.ystadmin.application.exception.TipsException;
import com.gdyunst.ystadmin.framework.domain.entity.admin.DepartmentEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.EmployeeEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.OrganizationEntity;

public class Employee extends EmployeeEntity {

    @Override
    public EmployeeEntity create() throws TipsException {
        String orgId = this.getOrgId();
        String departMentId = this.getDepartmentId();
        OrganizationEntity organizationEntity = new Organization();
        organizationEntity.setId(orgId);
        organizationEntity.load();
        this.setOrgName(organizationEntity.getText());
        DepartmentEntity departmentEntity = new Department();
        departmentEntity.setId(departMentId);
        departmentEntity.load();
        this.setDepartmentName(departmentEntity.getText());
        return super.create();
    }


    @Override
    public EmployeeEntity update() throws TipsException {
        String orgId = this.getOrgId();
        String departMentId = this.getDepartmentId();
        OrganizationEntity organizationEntity = new Organization();
        organizationEntity.setId(orgId);
        organizationEntity.load();
        this.setOrgName(organizationEntity.getText());
        DepartmentEntity departmentEntity = new Department();
        departmentEntity.setId(departMentId);
        departmentEntity.load();
        this.setDepartmentName(departmentEntity.getText());
        return super.update();
    }
}
