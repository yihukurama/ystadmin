package com.gdyunst.ystadmin.framework.service.domainservice.prepare.base;

import java.lang.reflect.Field;

public class Column {
    private String name;
    private String type;
    private String remark;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public static String getTypes(int type) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
        
        Field[] fields=java.sql.Types.class.getDeclaredFields();
        for(Field field:fields){
          //获取单个变量的值
           Class clazz = Class.forName("java.sql.Types");
           int value =  field.getInt(clazz);
           if(value==type){
               return field.getName();
           }
                
         }
        return "";
    }
    
    public static String sqlType2JavaType(String sqlType){
        String javaType = "";
        switch(sqlType){
        case "VARCHAR":
            javaType = "String";
            break;
        case "TIMESTAMP":
            javaType = "Date";
            break;
        case "INTEGER":
            javaType = "Integer";
            break;
        case "BIT":
            javaType = "Boolean";
            break;
        case "DOUBLE":
            javaType = "Double";
            break;
        case "DATETIME":
            javaType = "Date";
            break;
        case "INT":
            javaType = "Integer";
            break;
        default:
            javaType = "String";
        }
        
        return javaType;
    }
    
}
