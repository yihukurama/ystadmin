package com.gdyunst.ystadmin.framework.service.domainservice.prepare;

import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.Table;

public interface IGenEntity {
    public int genEntity(Table table);
    
    public String genEntColumns(Table table);
    public String genEntGetSet(Table table);
    public String genEntDesc(Table table);
    public String genEntName(Table table);
    public String genEntFileName(Table table);
    public String genEntToString(Table table);
}
