package com.gdyunst.ystadmin.framework.service.domainservice.prepare.base;

public class Mapper {
    private String mapperName;
    private String entName = "";                //entity名
    private String domainName = "";             //领域名
    private String tableName = "";              //表名
    private String dbColumns = "";              //表本身的字段  (id,num,acciTime) 注意去除createDate
    private String insertColumns = "";          //插入字段          (#{id},#{num},#{acciTime}) 注意去除createDate
    private String packages = "";               //entity的包
    private String updateColumns = "";          //mapper的更新字段
    private String listColumns = "";            //mapper的列表查询字段
    private String orderColumn = "";            //排序字段
    private String batchInsertColumns = "";     //批量插入字段
    
    
    
    public String getBatchInsertColumns() {
        return batchInsertColumns;
    }
    public void setBatchInsertColumns(String batchInsertColumns) {
        this.batchInsertColumns = batchInsertColumns;
    }
    public String getMapperName() {
        return mapperName;
    }
    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }
    public String getEntName() {
        return entName;
    }
    public void setEntName(String entName) {
        this.entName = entName;
    }
    public String getDomainName() {
        return domainName;
    }
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getDbColumns() {
        return dbColumns;
    }
    public void setDbColumns(String dbColumns) {
        this.dbColumns = dbColumns;
    }
    public String getInsertColumns() {
        return insertColumns;
    }
    public void setInsertColumns(String insertColumns) {
        this.insertColumns = insertColumns;
    }
    public String getPackages() {
        return packages;
    }
    public void setPackages(String packages) {
        this.packages = packages;
    }
    public String getUpdateColumns() {
        return updateColumns;
    }
    public void setUpdateColumns(String updateColumns) {
        this.updateColumns = updateColumns;
    }
    public String getListColumns() {
        return listColumns;
    }
    public void setListColumns(String listColumns) {
        this.listColumns = listColumns;
    }
    public String getOrderColumn() {
        return orderColumn;
    }
    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }
    
    
}
