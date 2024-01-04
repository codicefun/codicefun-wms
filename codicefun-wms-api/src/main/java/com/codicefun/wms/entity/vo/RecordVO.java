package com.codicefun.wms.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class RecordVO {

    private Integer id;
    private String operation;
    private String warehouse;
    private String goods;
    private String type;
    private Integer amount;
    private String username;
    private Date time;

}
