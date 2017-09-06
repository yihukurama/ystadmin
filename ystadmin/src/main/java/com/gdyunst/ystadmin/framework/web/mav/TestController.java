package com.gdyunst.ystadmin.framework.web.mav;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController {

    /**
     * 返回html模板.
     */
    @RequestMapping(value="/helloHtml",method = RequestMethod.GET)
    public String helloHtml(Model model) {

        model.addAttribute("hello", "fromTemplateController.helloHtml");

        return "/helloHtml";

    }
}
