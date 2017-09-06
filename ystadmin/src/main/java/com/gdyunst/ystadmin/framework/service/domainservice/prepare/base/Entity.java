package com.gdyunst.ystadmin.framework.service.domainservice.prepare.base;

public class Entity {

    private String packages = "";               //entity的包
    private String entColumns = "";             //entity的成员变量
    private String entGetSet = "";              //entity的get set 方法
    private String entDesc = "";                //entity的功能描述
    private String entName = "";                //entity名
    private String entFileName = "";            //entity文件名
    private String toString = "";               //toString方法
    
    
    public String getToString() {
        return toString;
    }
    public void setToString(String toString) {
        this.toString = toString;
    }
    public String getEntFileName() {
        return entFileName;
    }
    public void setEntFileName(String entFileName) {
        this.entFileName = entFileName;
    }
    public String getEntName() {
        return entName;
    }
    public void setEntName(String entName) {
        this.entName = entName;
    }
    public String getPackages() {
        return packages;
    }
    public void setPackages(String packages) {
        this.packages = packages;
    }
    public String getEntColumns() {
        return entColumns;
    }
    public void setEntColumns(String entColumns) {
        this.entColumns = entColumns;
    }
    public String getEntGetSet() {
        return entGetSet;
    }
    public void setEntGetSet(String entGetSet) {
        this.entGetSet = entGetSet;
    }
    public String getEntDesc() {
        return entDesc;
    }
    public void setEntDesc(String entDesc) {
        this.entDesc = entDesc;
    }
    
    
}
