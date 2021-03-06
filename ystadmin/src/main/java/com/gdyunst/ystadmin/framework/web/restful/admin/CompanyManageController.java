package com.gdyunst.ystadmin.framework.web.restful.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/company/api")
@Api(value = "/company/api", description = "公司组织管理接口")
public class CompanyManageController {

}
