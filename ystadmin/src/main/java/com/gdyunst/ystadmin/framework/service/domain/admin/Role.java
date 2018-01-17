package com.gdyunst.ystadmin.framework.service.domain.admin;

import com.alibaba.fastjson.JSON;
import com.gdyunst.ystadmin.application.exception.TipsException;
import com.gdyunst.ystadmin.application.utils.EmptyUtil;
import com.gdyunst.ystadmin.application.utils.YstUtils;
import com.gdyunst.ystadmin.framework.domain.entity.admin.*;
import com.gdyunst.ystadmin.framework.domain.repository.IObject;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.LogUtil;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 角色业务类
 * @Author: dengshuai
 * @Date: Created in 17:56 2018/1/3
 * @Modified: by autor in 17:56 2018/1/3
 */
public class Role extends RoleEntity {

    private List<Menu> menuPrivileges;                   //角色菜单权限
    private List<SubsystemEntity> subSystemPrivileges;   //角色系统权限
    private List<FuncEntity> funcPrivileges;             //角色功能权限
    private List<RoleprivilegeEntity> privileges;        //角色权限代码列表

    private String subSystemId;                          //筛选条件系统id
    private Integer type;                                //筛选条件权限类型

    /**
     * 更新条件，赋予菜单权限
     */
    private List<String> menuIds;
    /**
     * 更新条件，赋予功能权限
     */
    private List<String> funcIds;

    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }

    public List<String> getFuncIds() {
        return funcIds;
    }

    public void setFuncIds(List<String> funcIds) {
        this.funcIds = funcIds;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSubSystemId() {
        return subSystemId;
    }

    public void setSubSystemId(String subSystemId) {
        this.subSystemId = subSystemId;
    }

    public List<Menu> getMenuPrivileges() {
        return menuPrivileges;
    }

    public void setMenuPrivileges(List<Menu> menuPrivileges) {
        this.menuPrivileges = menuPrivileges;
    }

    public List<SubsystemEntity> getSubSystemPrivileges() {
        return subSystemPrivileges;
    }

    public void setSubSystemPrivileges(List<SubsystemEntity> subSystemPrivileges) {
        this.subSystemPrivileges = subSystemPrivileges;
    }

    public List<FuncEntity> getFuncPrivileges() {
        return funcPrivileges;
    }

    public void setFuncPrivileges(List<FuncEntity> funcPrivileges) {
        this.funcPrivileges = funcPrivileges;
    }

    public List<RoleprivilegeEntity> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<RoleprivilegeEntity> privileges) {
        this.privileges = privileges;
    }

    @Override
    public RoleEntity load() {
        if(this.getLoadRelate()){
            doGetRelationData();
        }
        super.load();

        return this;
    }

    /**
     * @Description: 获取功能编码列表
     * @Author: dengshuai
     * @Date: Created in 11:26 2018/1/15
     * @Modified: by autor in 11:26 2018/1/15
     */
    public List<RoleprivilegeEntity> doGetPrivileges(){
        if (!EmptyUtil.isEmpty(privileges)) {
            privileges.clear();
        } else {
            privileges = new ArrayList<>();
        }

        RoleprivilegeEntity rolePrivlege = new RoleprivilegeEntity();
        rolePrivlege.setRoleId(this.getId());
        if(!EmptyUtil.isEmpty(subSystemId)){
            rolePrivlege.setSubSystemId(subSystemId);
            rolePrivlege.setType(this.getType());
        }
        privileges = rolePrivlege.list();
        return privileges;
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

    public List<SubsystemEntity> doGetSubsystemPriviges()  {

        subSystemPrivileges = new ArrayList<>();
        if (!this.hasId())
            return subSystemPrivileges;
        doGetPrivileges();
        for (RoleprivilegeEntity privilege : privileges) {
            if (privilege.getType() == Roleprivilege.PRIVILEGE_TYPE_SYS) {
                LogUtil.DebugLog(this, "权限值是："+ JSON.toJSONString(privilege));
                String sysId = privilege.getPrivilegeId();
                SubsystemEntity subsystem = new Subsystem();
                subsystem.setId(sysId);
                subsystem.load();

                subSystemPrivileges.add(subsystem);
            }
        }
        return subSystemPrivileges;
    }

    public List<Menu> doGetMenuPriviges()  {

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
                    menu.doGetChildren();
                }else {
                    continue;
                }
                menuPrivileges.add(menu);
            }
        }
        return menuPrivileges;
    }

    @Override
    public IObject doGetRelationData() {
        super.doGetRelationData();
        doGetPrivileges();
        doGetSubsystemPriviges();
        doGetMenuPriviges();
        doGetFuncPriviges();
        return this;
    }

    @Override
    public Result list(int page, int limit) {
        Result result = super.list(page, limit);
        List<RoleEntity> roleEntities = (List<RoleEntity>) result.getData();
        List<Role> roleList = YstUtils.transferEntityList2DomainList(roleEntities,Role.class);
        for (int i = 0; i < roleList.size(); i++) {
            roleList.get(i).setSubSystemId(this.getSubSystemId());
            roleList.get(i).setType(this.getType());
            roleList.get(i).load();
        }
        result.setData(roleList);
        return result;
    }


    @Override
    public RoleEntity update() throws TipsException {
        //清楚角色对应的所有功能
        Roleprivilege roleprivilege = new Roleprivilege();
        roleprivilege.setRoleId(this.getId());
        roleprivilege.remove();
        if(!EmptyUtil.isEmpty(menuIds)){//更新菜单权限
            for (int i = 0; i < menuIds.size(); i++) {
                Roleprivilege menuprivilege = new Roleprivilege();
                menuprivilege.setRoleId(this.getId());
                menuprivilege.setType(Roleprivilege.PRIVILEGE_TYPE_MENU);
                menuprivilege.setSubSystemId(this.getSubSystemId());
                menuprivilege.setPrivilegeId(menuIds.get(i));
                menuprivilege.create();
            }
            Roleprivilege menuprivilege = new Roleprivilege();
            menuprivilege.setRoleId(this.getId());
            menuprivilege.setType(Roleprivilege.PRIVILEGE_TYPE_SYS);
            menuprivilege.setSubSystemId(this.getSubSystemId());
            menuprivilege.setPrivilegeId(this.getSubSystemId());
        }

        if(!EmptyUtil.isEmpty(funcIds)){//更新功能权限
            for (int i = 0; i < funcIds.size(); i++) {
                Roleprivilege funcPrivilege = new Roleprivilege();
                funcPrivilege.setRoleId(this.getId());
                funcPrivilege.setType(Roleprivilege.PRIVILEGE_TYPE_FUNC);
                funcPrivilege.setSubSystemId(this.getSubSystemId());
                funcPrivilege.setPrivilegeId(funcIds.get(i));
                funcPrivilege.create();
            }
        }
        return super.update();
    }
}
