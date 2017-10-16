package com.gdyunst.ystadmin.framework.service.domain.admin;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.gdyunst.ystadmin.application.exception.CrudException;
import com.gdyunst.ystadmin.application.utils.EmptyUtil;
import com.gdyunst.ystadmin.framework.domain.entity.admin.DepartmentEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.EmployeeEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.FuncEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.MenuEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.OrganizationEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.RoleEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.RoleprivilegeEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.UserroleEntity;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.LogUtil;

public class User extends UserEntity {
	EmployeeEntity emp; // 用户员工信息
	OrganizationEntity org; // 用户机构信息
	DepartmentEntity department; // 用户部门信息
	String realName; // 用户真实姓名
	List<RoleEntity> roles; // 用户拥有的角色
	List<RoleprivilegeEntity> privileges; // 用户拥有的权限
	List<Menu> menuPrivileges; // 用户的菜单权限
	List<FuncEntity> funcPrivileges; // 用户功能权限

	public List<Menu> getMenuPrivileges() {
		return menuPrivileges;
	}

	public void setMenuPrivileges(List<Menu> menuPrivileges) {
		this.menuPrivileges = menuPrivileges;
	}

	public List<FuncEntity> getFuncPrivileges() {
		return funcPrivileges;
	}

	public void setFuncPrivileges(List<FuncEntity> funcPrivileges) {
		this.funcPrivileges = funcPrivileges;
	}

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

	public List<Menu> doGetMenuPriviges() throws CrudException {

		if (menuPrivileges == null)
			menuPrivileges = new ArrayList<>();
		if (!this.hasId())
			return menuPrivileges;
		doGetPrivileges();
		for (RoleprivilegeEntity privilege : privileges) {
			if (privilege.getType() == Roleprivilege.PRIVILEGE_TYPE_MENU) {
				LogUtil.DebugLog(this, "权限值是："+JSON.toJSONString(privilege));
				String menuId = privilege.getPrivilegeId();
				Menu menu = new Menu();
				menu.setId(menuId);
				MenuEntity menuEntity = menu.load();
				if (menuEntity!=null && menu.getParentId().equals(Menu.PATH_ROOT)) {// 根菜单，寻找其子菜单
					menu.doGetSubMenus();
				}else {
					continue;
				}
				menuPrivileges.add(menu);
			}
		}
		return menuPrivileges;
	}

	public List<FuncEntity> doGetFuncPriviges() throws CrudException {

		if (funcPrivileges == null)
			funcPrivileges = new ArrayList<>();
		if (!this.hasId())
			return funcPrivileges;
		doGetPrivileges();
		for (RoleprivilegeEntity privilege : privileges) {
			if (privilege.getType() == Roleprivilege.PRIVILEGE_TYPE_FUNC) {
				String funcId = privilege.getPrivilegeId();
				FuncEntity func = new FuncEntity();
				func.setId(funcId);
				func.load();

				funcPrivileges.add(func);
			}
		}
		return funcPrivileges;
	}

	public EmployeeEntity doGetEmp() throws CrudException {
		emp = new EmployeeEntity();
		emp.setId(this.getEmployeeId());
		emp.load();
		return emp;
	}

	public String doGetRealName() throws CrudException {
		emp = new EmployeeEntity();
		emp.setId(this.getEmployeeId());
		emp.load();
		return emp.getRealName();
	}

	public OrganizationEntity doGetOrg() throws CrudException {
		doGetEmp();
		org = new OrganizationEntity();
		org.setId(emp.getOrgId());
		org.load();
		return org;
	}

	public DepartmentEntity doGetDepartment() throws CrudException {
		doGetEmp();
		department = new DepartmentEntity();
		department.setId(emp.getDepartmentId());
		department.load();
		return department;
	}

	public List<RoleEntity> doGetRoles() throws CrudException {
		if (!EmptyUtil.isEmpty(roles)) {
			roles.clear();
		} else {
			roles = new ArrayList<>();
		}
		UserroleEntity userRole = new UserroleEntity();
		userRole.setUserId(this.getId());
		List<UserroleEntity> userRoles = userRole.list();
		if (!EmptyUtil.isEmpty(userRoles)) {
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

	public List<RoleprivilegeEntity> doGetPrivileges() throws CrudException {
		if (!EmptyUtil.isEmpty(privileges)) {
			privileges.clear();
		} else {
			privileges = new ArrayList<>();
		}

		for (RoleEntity role : roles) {
			RoleprivilegeEntity rolePrivlege = new RoleprivilegeEntity();
			rolePrivlege.setRoleId(role.getId());
			List<RoleprivilegeEntity> rolePrivleges = rolePrivlege.list();
			if (!EmptyUtil.isEmpty(rolePrivleges)) {
				privileges.addAll(rolePrivleges);
			}
		}
		return privileges;

	}

	public User doGetRelationData() throws CrudException {
		doGetEmp();
		doGetOrg();
		doGetDepartment();
		doGetRoles();
		doGetPrivileges();
		doGetMenuPriviges();
		doGetFuncPriviges();
		return this;
	}
}
