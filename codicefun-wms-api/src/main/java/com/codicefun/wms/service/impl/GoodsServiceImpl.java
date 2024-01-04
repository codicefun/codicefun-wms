package com.codicefun.wms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codicefun.wms.entity.po.Goods;
import com.codicefun.wms.entity.vo.GoodsVO;
import com.codicefun.wms.mapper.GoodsMapper;
import com.codicefun.wms.service.GoodsService;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public GoodsVO getById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public IPage<GoodsVO> getPage(IPage<GoodsVO> page) {
        return baseMapper.selectPage(page);
    }

}
