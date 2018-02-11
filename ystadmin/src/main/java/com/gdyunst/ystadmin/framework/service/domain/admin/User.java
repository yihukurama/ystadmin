package com.gdyunst.ystadmin.framework.service.domain.admin;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gdyunst.ystadmin.application.component.SpringBeanTools;
import com.gdyunst.ystadmin.application.exception.TipsException;
import com.gdyunst.ystadmin.application.utils.EmptyUtil;
import com.gdyunst.ystadmin.framework.domain.entity.admin.*;
import com.gdyunst.ystadmin.framework.service.domainservice.admin.ISecurity;
import com.gdyunst.ystadmin.framework.service.domainservice.admin.impl.SecurityService;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.LogUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class User extends UserEntity {

	List<String> roleIds;	//创建用户时同时配置角色
	String secret;   //登录成功的secret
	boolean resetPwd;    //重置密码时必须传的参数

	EmployeeEntity emp; // 用户员工信息
	OrganizationEntity org; // 用户机构信息
	DepartmentEntity department; // 用户部门信息
	String realName; // 用户真实姓名
	List<RoleEntity> roles; // 用户拥有的角色
	List<RoleprivilegeEntity> privileges; // 用户拥有的权限
	List<Menu> menuPrivileges; // 用户的菜单权限
	List<FuncEntity> funcPrivileges; // 用户功能权限
	List<SubsystemEntity> subSystemPrivileges; // 用户拥有的系统权限
	SubsystemEntity subsystemEntity; 	//用户当前登录的系统

	public List<String> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}

	public List<SubsystemEntity> getSubSystemPrivileges() {
		return subSystemPrivileges;
	}

	public void setSubSystemPrivileges(List<SubsystemEntity> subSystemPrivileges) {
		this.subSystemPrivileges = subSystemPrivileges;
	}

	public boolean isResetPwd() {
		return resetPwd;
	}

	public void setResetPwd(boolean resetPwd) {
		this.resetPwd = resetPwd;
	}

	public SubsystemEntity getSubsystemEntity() {
		return subsystemEntity;
	}

	public void setSubsystemEntity(SubsystemEntity subsystemEntity) {
		this.subsystemEntity = subsystemEntity;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

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

	public List<Menu> doGetMenuPriviges()  {

		if (menuPrivileges == null)
			menuPrivileges = new ArrayList<>();
		if (!this.hasId())
			return menuPrivileges;
		doGetPrivileges();
		for (RoleprivilegeEntity privilege : privileges) {
			if (privilege.getType() == Roleprivilege.PRIVILEGE_TYPE_MENU) {
				LogUtil.DebugLog(this, "菜单权限值是："+JSON.toJSONString(privilege));
				String menuId = privilege.getPrivilegeId();
				Menu menu = new Menu();
				menu.setId(menuId);
				MenuEntity menuEntity = menu.load();
				if (menuEntity!=null && menu.getParentId().equals(Menu.PATH_ROOT)) {// 根菜单，寻找其子菜单
					menu.doGetChildren();
				}else {
					continue;
				}
				menuPrivileges.add(menu);
			}
		}
		return menuPrivileges;
	}

	public List<SubsystemEntity> doGetSubsystemPriviges()  {

		if (subSystemPrivileges == null)
			subSystemPrivileges = new ArrayList<>();
		if (!this.hasId())
			return subSystemPrivileges;
		doGetPrivileges();
		for (RoleprivilegeEntity privilege : privileges) {
			if (privilege.getType() == Roleprivilege.PRIVILEGE_TYPE_SYS) {
				LogUtil.DebugLog(this, "系统权限值是："+JSON.toJSONString(privilege));
				String sysId = privilege.getPrivilegeId();
				SubsystemEntity subsystem = new Subsystem();
				subsystem.setId(sysId);
				subsystem.load();

				subSystemPrivileges.add(subsystem);
			}
		}
		return subSystemPrivileges;
	}

	public List<FuncEntity> doGetFuncPriviges()  {

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

	public EmployeeEntity doGetEmp()  {
		emp = new EmployeeEntity();
		emp.setId(this.getEmployeeId());
		emp.load();
		return emp;
	}

	public String doGetRealName()  {
		emp = new EmployeeEntity();
		emp.setId(this.getEmployeeId());
		emp.load();
		return emp.getRealName();
	}

	public OrganizationEntity doGetOrg()  {
		doGetEmp();
		org = new OrganizationEntity();
		org.setId(emp.getOrgId());
		org.load();
		return org;
	}

	public DepartmentEntity doGetDepartment()  {
		doGetEmp();
		department = new DepartmentEntity();
		department.setId(emp.getDepartmentId());
		department.load();
		return department;
	}

	public List<RoleEntity> doGetRoles()  {
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

	public List<RoleprivilegeEntity> doGetPrivileges()  {
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

	public User doGetRelationData() {
		doGetEmp();
		doGetOrg();
		doGetDepartment();
		doGetRoles();
		doGetPrivileges();
		doGetSubsystemPriviges();
		doGetMenuPriviges();
		doGetFuncPriviges();
		return this;
	}

	@Override
	public UserEntity create() throws TipsException{
		String account = this.getUsername();
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(account);
		if(!EmptyUtil.isEmpty(userEntity.list())){
			throw new TipsException("用户名重复");
		}
		EmployeeEntity employeeEntity = new Employee();
		employeeEntity.setId(this.getEmployeeId());
		employeeEntity.load();
		this.setEmployeeName(employeeEntity.getRealName());

		SecurityService securityService = (SecurityService) SpringBeanTools.getBean(ISecurity.class);
		String encrypetPwd = securityService.pwdEncrypt(this.getPassword());
		this.setPassword(encrypetPwd);
		super.create();

		if(!EmptyUtil.isEmpty(roleIds)){
			List<UserroleEntity> userroleEntities = new ArrayList<>();
			for (int i = 0; i < roleIds.size(); i++) {
				UserroleEntity userRole = new Userrole();
				userRole.setUserId(this.getId());
				userRole.setRoleId(roleIds.get(i));
				userroleEntities.add(userRole);
			}
			Userrole userrole = new Userrole();
			userrole.creates(userroleEntities);
		}

		return this;
	}

	@Override
	public int remove() {
		UserroleEntity userroleEntity = new Userrole();
		userroleEntity.setUserId(this.getId());
		userroleEntity.remove();
		return super.remove();
	}

	@Override
	public UserEntity update() throws TipsException {
		if(resetPwd){
			String pwd = this.getPassword();
			SecurityService securityService = (SecurityService) SpringBeanTools.getBean(ISecurity.class);
			this.setPassword(securityService.pwdEncrypt(pwd));
		}
		return super.update();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		User user =  this;
		return user.getId().hashCode();
	}


}
