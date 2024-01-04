package com.codicefun.wms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codicefun.wms.entity.po.Goods;
import com.codicefun.wms.entity.vo.GoodsVO;

public interface GoodsService extends IService<Goods> {

    GoodsVO getById(Long id);

    IPage<GoodsVO> getPage(IPage<GoodsVO> page);

}
