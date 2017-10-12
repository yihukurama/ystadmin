package com.gdyunst.ystadmin.framework.service.domainservice.admin.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSON;
import com.gdyunst.ystadmin.application.config.SystemConfig;
import com.gdyunst.ystadmin.application.exception.CrudException;
import com.gdyunst.ystadmin.framework.domain.entity.admin.FuncEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.MenuEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;
import com.gdyunst.ystadmin.framework.service.domain.admin.Menu;
import com.gdyunst.ystadmin.framework.service.domain.admin.User;
import com.gdyunst.ystadmin.framework.service.domainservice.admin.IViewService;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.LogUtil;
@Service
public class ViewService implements IViewService {

	@Autowired
	SystemConfig systemConfig;
    @Override
    public Model initAdminIndex(Model model,String userId) {
       
        User user = new User();
        user.setId(userId);
        UserEntity userEntity;
        try {
            userEntity = user.load();
            if(userEntity == null){
                return null;
            }
            user.doGetRelationData();
            List<Menu> userMenus = new ArrayList<Menu>();
            List<Menu> userMenu = user.getMenuPrivileges();
            for (Menu menu : userMenu) {
				if(menu.getSubSystemId().equals(systemConfig.getAdminSystemId())) {
					userMenus.add(menu);
				}
			}
            List<FuncEntity> userFuncs = new ArrayList<FuncEntity>();
            List<FuncEntity> userFunc = user.getFuncPrivileges();
            for (FuncEntity funcEntity : userFunc) {
				if(funcEntity.getSubSystemId().equals(systemConfig.getAdminSystemId())) {
					userFuncs.add(funcEntity);
				}
			}
            
            model.addAttribute("userName", user.getUsername());
            model.addAttribute("menus", userMenus);
        } catch (CrudException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
       
       
        return model;
    }

    @Override
    public Model initAdminMenuIndex(Model model, String menuId) {
        MenuEntity menu = new MenuEntity();
        menu.setId(menuId);
        try {
            menu = menu.load();
            if(menu == null){
                return null;
            }
            
            model.addAttribute("userName", menu.getText());
            
        } catch (CrudException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
       
       
        return model;
    }

}
