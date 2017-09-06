package com.gdyunst.ystadmin.framework.service.domainservice.prepare.base;

import java.util.List;

public class Table {
    private String name;
    private String remark;
    private List<Column> columns;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public List<Column> getColumns() {
        return columns;
    }
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
    
    
}
