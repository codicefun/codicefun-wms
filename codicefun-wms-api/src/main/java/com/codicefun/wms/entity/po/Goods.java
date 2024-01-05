package com.codicefun.wms.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Goods {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer warehouseId;

    private Integer goodsTypeId;

    private Integer amount;

    private Integer loss;

    private String description;

}
