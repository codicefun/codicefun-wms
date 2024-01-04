package com.codicefun.wms.entity.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Record {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String operation;

    private Integer warehouseId;

    private Integer goodsId;

    private Integer typeId;

    private Integer amount;

    private Integer userId;

    private Date time;

}
