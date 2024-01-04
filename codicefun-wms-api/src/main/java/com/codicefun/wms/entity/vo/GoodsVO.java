package com.codicefun.wms.entity.vo;

import lombok.Data;

@Data
public class GoodsVO {

    private Long id;
    private String name;
    private String warehouse;
    private String type;
    private Long amount;
    private String description;

}
