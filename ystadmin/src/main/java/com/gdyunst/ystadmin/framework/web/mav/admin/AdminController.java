package com.gdyunst.ystadmin.framework.web.mav.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdyunst.ystadmin.framework.service.domainservice.admin.IViewService;

/**
 * 功能描述:管理后台的页面 
 * @Author:denghsuai
 * @Date:2017年8月30日 下午5:24:40
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    IViewService viewService;
    
    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(Model model,@RequestParam String userId) {
        model = viewService.initAdminIndex(model,userId);
        if(model == null){
            return "/public/index";
        }
        return "/admin/index";
    }

    @RequestMapping(value="/main",method = RequestMethod.GET)
    public String main(Model model) {
//        model = viewService.initAdminMenuIndex(model,menuId);
//        if(model == null){
//            return "/public/index";
//        }
        return "/admin/main";
    }
    
}
