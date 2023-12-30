package com.codicefun.wms.entity.enums;

import lombok.Getter;

@Getter
public enum ResponseStatusEnum {

    SUCCESS(200, "success"),
    FAIL(400, "fail");

    private final int code;
    private final String message;

    ResponseStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
