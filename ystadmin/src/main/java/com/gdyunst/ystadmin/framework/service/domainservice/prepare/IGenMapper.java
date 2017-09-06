package com.gdyunst.ystadmin.framework.service.domainservice.prepare;

import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.Table;

public interface IGenMapper {
    
    public int genMapper(Table table);
    public int genBaseMapper(Table table);
    public int genTreeMapper(Table table);
    public String genDBColumns(Table table);
    public String genInsertColumns(Table table);
    public String genBatchInsertColumns(Table table);
    public String genUpdateColumns(Table table);
    public String genListColumns(Table table);
    public String genOrderColumn(Table table);
    
}
