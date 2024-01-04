package com.codicefun.wms.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Warehouse {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String description;

}
