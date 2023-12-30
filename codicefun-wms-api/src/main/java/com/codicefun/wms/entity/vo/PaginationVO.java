package com.codicefun.wms.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginationVO<T> {

    private Long total;
    private Long current;
    private Long size;
    private List<T> list;

}
