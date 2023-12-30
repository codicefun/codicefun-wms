package com.codicefun.wms.entity.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.codicefun.wms.entity.enums.ResponseStatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseVO<T> {

    private int code;
    private String message;
    private T data;

    public static <T> ResponseVO<T> success() {
        return success(null);
    }

    public static <T> ResponseVO<T> success(T data) {
        return ResponseVO.<T>builder()
                         .code(ResponseStatusEnum.SUCCESS.getCode())
                         .message(ResponseStatusEnum.SUCCESS.getMessage())
                         .data(data)
                         .build();
    }

    public static <T> ResponseVO<T> fail() {
        return fail(null);
    }

    public static <T> ResponseVO<T> fail(T data) {
        return ResponseVO.<T>builder()
                         .code(ResponseStatusEnum.FAIL.getCode())
                         .message(ResponseStatusEnum.FAIL.getMessage())
                         .data(data)
                         .build();
    }

    public static <T> ResponseVO<PaginationVO<T>> page(IPage<T> data) {
        PaginationVO<T> paginationVO =
                new PaginationVO<>(data.getTotal(), data.getCurrent(), data.getSize(), data.getRecords());

        return success(paginationVO);
    }

}
