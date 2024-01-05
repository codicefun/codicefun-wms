package com.codicefun.wms.entity.vo;

import lombok.Data;

@Data
public class GoodsVO {

    private Integer id;
    private String name;
    private String warehouse;
    private String type;
    private Integer amount;
    private Integer loss;
    private String description;

}
