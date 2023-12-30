package com.codicefun.wms.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginationVO<T> {

    public Long total;
    public Long current;
    public Long size;
    public List<T> list;

}
