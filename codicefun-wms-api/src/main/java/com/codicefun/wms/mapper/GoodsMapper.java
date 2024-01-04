package com.codicefun.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.codicefun.wms.entity.po.Goods;
import com.codicefun.wms.entity.vo.GoodsVO;

public interface GoodsMapper extends BaseMapper<Goods> {

    GoodsVO selectVOById(Integer id);

    IPage<GoodsVO> selectVOPage(IPage<GoodsVO> page);

}
