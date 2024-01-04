package com.codicefun.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codicefun.wms.mapper.GoodsTypeMapper;
import com.codicefun.wms.entity.po.GoodsType;
import com.codicefun.wms.service.GoodsTypeService;
import org.springframework.stereotype.Service;

@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType> implements GoodsTypeService {

    @Override
    public String getNameById(Long id) {
        return getById(id).getName();
    }

}
