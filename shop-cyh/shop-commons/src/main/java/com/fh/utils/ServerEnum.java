package com.fh.utils;

public enum ServerEnum {

    ACCOUNT_OR_PWD_ISNULL(1001,"账号或者密码不存在"),
    ACCOUNT_ERROR(5001,"账号异常，请联系管理员")
    ,ACCOUNT_NOT_EXIST(1002,"账号不存在请联系管理员")
    ,SAFETY_ERROR(1006,"失败")
    ,ACCOUNT_NOT_INVALID(1003,"账号无效请联系管理员")
    ,PWD_ERROR(1004,"密码输入有误，请重新输入")
    ,RIGHT_NO(1005,"该用户没有权限访问，请联系管理员")
    ,TOKEN_TIMEOUT(6001,"token失效了，请重新登录")
    ,SECRET_ERROR(6001,"token验证失败，非法请求")
    ,EXPORT_NULL(7001,"导出数据是空的")
    ,YZM_ERREO(8001,"验证码错误")
    ,SUCCESS(200,"成功")
    ,ERROR(500,"失败")
    ,SERVER_CONNECT_ERROR(9000,"服务器连接失败")
    ,SERVER_BUSYNESS(9001,"接口连接超时")
    ,SERVER_ERROR(9002,"IO读取失败")
    ;
    private ServerEnum(Integer code, String message) {
        this.code=code;
        this.message=message;
    }
    private Integer code;
    private String message;


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
