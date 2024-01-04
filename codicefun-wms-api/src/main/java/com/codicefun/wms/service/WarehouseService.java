package com.codicefun.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.codicefun.wms.entity.po.Warehouse;

public interface WarehouseService extends IService<Warehouse> {

    String getNameById(Long id);

}
