package com.codicefun.wms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codicefun.wms.entity.po.Goods;
import com.codicefun.wms.entity.vo.GoodsVO;

public interface GoodsService extends IService<Goods> {

    GoodsVO getVOById(Integer id);

    IPage<GoodsVO> pageVO(IPage<GoodsVO> page);

    String getNameById(Integer id);

}
