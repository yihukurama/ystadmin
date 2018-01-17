package com.gdyunst.ystadmin.framework.service.domain.admin;

import java.util.ArrayList;
import java.util.List;

import com.gdyunst.ystadmin.framework.domain.entity.admin.MenuEntity;

public class Menu extends MenuEntity{

	public final static String PATH_ROOT = "root"; 


    public List<Object> doGetChildren() {
        if(!this.hasId()){
            return new ArrayList<>();
        }
        if(children==null){
            children=new ArrayList<>();
        }
        this.load();
        MenuEntity queryMenu = new MenuEntity();
        queryMenu.setParentId(this.getId());
        children = queryMenu.list();
        return children;
    }
}
