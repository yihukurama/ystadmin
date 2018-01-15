package com.gdyunst.ystadmin.framework.service.domainservice.prepare.base;

public class PrepareConstant {
    public static final String PROJECT = "ystadmin";
    public final static String DBCOLUMN_CREATEDATE="createDate";
    public final static String DBCOLUMN_INDEXORDER="indexOrder";
    public final static String DBCOLUMN_DELSTATUS="delStatus";
    public final static String DBCOLUMN_PARENTID="parentId";
    public static final String ENTITYTPL_PATH = "src/main/java/com/gdyunst/"+PROJECT+"/framework/service/domainservice/prepare/base/templates/framework/entity/BaseEntity.tpl";
    public static final String TREE_MAPPERTPL_PATH = "src/main/java/com/gdyunst/"+PROJECT+"/framework/service/domainservice/prepare/base/templates/framework/mapper/BaseTreeMapper.xml";
    public static final String NORMAL_MAPPERTPL_PATH = "src/main/java/com/gdyunst/"+PROJECT+"/framework/service/domainservice/prepare/base/templates/framework/mapper/BaseNormalMapper.xml";
    
    
    public static String packages = "com.gdyunst."+PROJECT+".framework.domain.entity.admin";
    public static String businesspackages = "com.gdyunst."+PROJECT+".framework.domain.entity.business";
    public static String mapperPath = "src/main/resources/mapper/basemapper/admin";
    public static String mapperBusinessPath = "src/main/resources/mapper/basemapper/business";
    public static String entityPath = "src/main/java/com/gdyunst/"+PROJECT+"/framework/domain/entity/admin";
    public static String entityBusinessPath = "src/main/java/com/gdyunst/"+PROJECT+"/framework/domain/entity/business";
    
    //司法调解系统

}
