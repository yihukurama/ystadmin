package com.gdyunst.ystadmin.framework.service.domain.admin;

import java.util.ArrayList;
import java.util.List;

import com.gdyunst.ystadmin.application.exception.CrudException;
import com.gdyunst.ystadmin.framework.domain.entity.admin.MenuEntity;

public class Menu extends MenuEntity{

    private List<MenuEntity> subMenus;

    public List<MenuEntity> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<MenuEntity> subMenus) {
        this.subMenus = subMenus;
    }
    
    public List<MenuEntity> doGetSubMenus() throws CrudException{
        if(!this.hasId()){
            return new ArrayList<MenuEntity>();
        }
        if(subMenus==null){
            subMenus=new ArrayList<>();
        }
        this.load();
        MenuEntity queryMenu = new MenuEntity();
        queryMenu.setParentId(this.getId());
        subMenus = queryMenu.list();
        return subMenus;
    }
}
