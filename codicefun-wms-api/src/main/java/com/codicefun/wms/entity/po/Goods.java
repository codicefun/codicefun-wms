package com.codicefun.wms.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Goods {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Long warehouseId;

    private Long goodsTypeId;

    private Long amount;

    private String description;

}
