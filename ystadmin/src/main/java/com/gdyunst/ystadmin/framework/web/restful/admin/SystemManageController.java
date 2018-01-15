package com.gdyunst.ystadmin.framework.web.restful.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/system/api")
@Api(value = "/system/api", description = "系统管理接口")
public class SystemManageController {


}
