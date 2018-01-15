package com.gdyunst.ystadmin.framework.web.restful.admin;

import com.gdyunst.ystadmin.framework.web.restful.CommController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/AdminBaseController")
public class AdminBaseController extends CommController {
	
	
	public AdminBaseController() {
		super();
		packageD = "com.gdyunst.ystadmin.framework.service.domain.admin.";
	}

}
