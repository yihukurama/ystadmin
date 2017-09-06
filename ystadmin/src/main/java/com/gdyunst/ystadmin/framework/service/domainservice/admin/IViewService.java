package com.gdyunst.ystadmin.framework.service.domainservice.admin;

import org.springframework.ui.Model;

public interface IViewService {

    public Model initAdminIndex(Model model,String userId);

    public Model initAdminMenuIndex(Model model, String menuId);
}
