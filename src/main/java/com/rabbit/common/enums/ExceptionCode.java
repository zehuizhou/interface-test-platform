package com.rabbit.common.enums;

public enum ExceptionCode {
    SYS_ERROR(500, "系统异常，请联系管理员"),
    PERMISSION_ERROR(501, "您没有此操作权限");

    private final int code;
    private final String name;

    private ExceptionCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
