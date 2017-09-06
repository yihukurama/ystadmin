package com.gdyunst.ystadmin.application.constant;

public abstract class Constant {

    public static final int DEL_STATUS_1 = 1;
    public static final int DEL_STATUS_0 = 0;

    public static final int DB_TYPE_1 = 1; // 数据库:[角色权限表type 系统] 程序[员工类型type
    // 本公司]
    public static final int DB_TYPE_2 = 2; // 数据库:[角色权限表type 模块] 程序[员工类型type 保险]
    public static final int DB_TYPE_3 = 3; // 数据库:[角色权限表type 菜单] 程序[员工类型type 交警]
    public static final int DB_TYPE_4 = 4; // 数据库:[角色权限表type 功能] 程序[员工类型type 法院]

    public static final String JWTSECRET = "com.gdyunst";

    public static final Integer PAGE_NUMBER = 1; // 分页查询默认页码;
    public static final Integer PAGE_SIZE = 10; // 分页查询默认每页显示数据条数;
    public static final int RECORD_VERIFYCODE_SIZE = 4; // 查看案件记录验证码位数;
    public static final Long VCODE_EXPIRETIME = 300L; // 登录验证码有效时间;
    public static Long LOGIN_EXPIRETIME = 28800L; // 登录有效时间28800秒,8个小时
    public static final Long AUTHS_EXPIRETIME = 3800L; // 权限有效3800秒,1个小时
}
