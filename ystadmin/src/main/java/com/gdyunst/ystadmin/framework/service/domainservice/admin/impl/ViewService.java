package com.gdyunst.ystadmin.framework.service.domainservice.admin.impl;



import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdyunst.ystadmin.application.exception.CrudException;
import com.gdyunst.ystadmin.framework.domain.entity.admin.MenuEntity;
import com.gdyunst.ystadmin.framework.domain.entity.admin.UserEntity;
import com.gdyunst.ystadmin.framework.service.domain.admin.User;
import com.gdyunst.ystadmin.framework.service.domainservice.admin.IViewService;
@Service
public class ViewService implements IViewService {

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
            model.addAttribute("userName", user.getUsername());
            model.addAttribute("menus", user.getMenuPrivileges());
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
