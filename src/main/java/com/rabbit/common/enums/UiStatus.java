package com.rabbit.common.enums;

public enum UiStatus {
    SUCCESS(0, "成功"),
    FAIL(1, "失败"),
    SKIP(2, "跳过");

    private final int code;

    private final String codeName;

    private UiStatus(int code, String codeName) {
        this.code = code;
        this.codeName = codeName;
    }

    public int getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }
}
