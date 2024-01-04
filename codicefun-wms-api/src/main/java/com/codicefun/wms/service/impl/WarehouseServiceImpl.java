package com.codicefun.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codicefun.wms.mapper.WarehouseMapper;
import com.codicefun.wms.entity.po.Warehouse;
import com.codicefun.wms.service.WarehouseService;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {

    @Override
    public String getNameById(Long id) {
        return getById(id).getName();
    }

}
