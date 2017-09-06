package com.gdyunst.ystadmin.framework.web.mav.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 功能描述:公开的页面 
 * @Author:denghsuai
 * @Date:2017年8月30日 下午5:24:40
 */
@Controller
@RequestMapping("/public")
public class PublicController {

    
    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(Model model) {
        
        return "/login/index";
    }
    
   
}
