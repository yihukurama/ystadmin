package com.gdyunst.ystadmin.framework.service.domain.admin;

import java.util.ArrayList;
import java.util.List;

import com.gdyunst.ystadmin.application.exception.CrudException;
import com.gdyunst.ystadmin.application.utils.JavaUtil;
import com.gdyunst.ystadmin.framework.domain.entity.admin.DepartmentEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.EmployeeEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.OrganizationEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.RoleEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.RoleprivilegeEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.UserroleEntity;

public class User extends UserEntity {
    EmployeeEntity emp;                 //用户员工信息
    OrganizationEntity org;             //用户机构信息
    DepartmentEntity department;        //用户部门信息
    String realName;                    //用户真实姓名
    List<RoleEntity> roles;             //用户拥有的角色
    List<RoleprivilegeEntity> privileges;   //用户拥有的权限
    
    public EmployeeEntity getEmp() {
        return emp;
    }

    public void setEmp(EmployeeEntity emp) {
        this.emp = emp;
    }

    public OrganizationEntity getOrg() {
        return org;
    }

    public void setOrg(OrganizationEntity org) {
        this.org = org;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public List<RoleprivilegeEntity> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<RoleprivilegeEntity> privileges) {
        this.privileges = privileges;
    }

    public EmployeeEntity doGetEmp() throws CrudException{
        emp = new EmployeeEntity();
        emp.setId(this.getEmployeeId());
        emp.load();
        return emp;
    }
    
    public String doGetRealName() throws CrudException{
        emp = new EmployeeEntity();
        emp.setId(this.getEmployeeId());
        emp.load();
        return emp.getRealName();
    }
    
    public OrganizationEntity doGetOrg() throws CrudException{
        doGetEmp();
        org = new OrganizationEntity();
        org.setId(emp.getOrgId());
        org.load();
        return org;
    }
    
    public DepartmentEntity doGetDepartment() throws CrudException{
        doGetEmp();
        department = new DepartmentEntity();
        department.setId(emp.getDepartmentId());
        department.load();
        return department;
    }
    
    public List<RoleEntity> doGetRoles() throws CrudException{
        if(!JavaUtil.isEmpty(roles)){
            roles.clear();
        }else{
            roles = new ArrayList<>();
        }
        UserroleEntity userRole = new UserroleEntity();
        userRole.setUserId(this.getId());
        List<UserroleEntity> userRoles = userRole.list();
        if(!JavaUtil.isEmpty(userRoles)){
            for (UserroleEntity userroleEntity : userRoles) {
                String roleId = userroleEntity.getRoleId();
                RoleEntity role = new RoleEntity();
                role.setId(roleId);
                role.load();
                roles.add(role);
            }
        }
        return roles;
    }
    
    public List<RoleprivilegeEntity> doGetPrivileges() throws CrudException{
        if(!JavaUtil.isEmpty(privileges)){
            privileges.clear();
        }else{
            privileges = new ArrayList<>();
        }
        
        for (RoleEntity role : roles) {
            RoleprivilegeEntity rolePrivlege = new RoleprivilegeEntity();
            rolePrivlege.setRoleId(role.getId());
            List<RoleprivilegeEntity> rolePrivleges = rolePrivlege.list();
            if(!JavaUtil.isEmpty(rolePrivleges)){
                privileges.addAll(rolePrivleges);
            }
        }
        return privileges;
        
    }
    
    public User doGetRelationData() throws CrudException{
        doGetEmp();
        doGetOrg();
        doGetDepartment();
        doGetRoles();
        doGetPrivileges();
        return this;
    }
}
