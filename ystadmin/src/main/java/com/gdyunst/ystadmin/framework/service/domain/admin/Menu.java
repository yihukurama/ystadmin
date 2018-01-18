package com.gdyunst.ystadmin.framework.service.domain.admin;

import java.util.ArrayList;
import java.util.List;

import com.gdyunst.ystadmin.application.utils.EmptyUtil;
import com.gdyunst.ystadmin.framework.domain.entity.admin.MenuEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.SubsystemEntity;

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

    @Override
    public MenuEntity create() {
        if(!EmptyUtil.isEmpty(this.getSubSystemId())){
            SubsystemEntity subsystemEntity = new Subsystem();
            subsystemEntity.setId(this.getSubSystemId());
            subsystemEntity.load();
            this.setSubSystemName(subsystemEntity.getText());
        }
        return super.create();
    }


    @Override
    public MenuEntity update() {
        if(!EmptyUtil.isEmpty(this.getSubSystemId())){
            SubsystemEntity subsystemEntity = new Subsystem();
            subsystemEntity.setId(this.getSubSystemId());
            subsystemEntity.load();
            this.setSubSystemName(subsystemEntity.getText());
        }
        return super.update();
    }
}
