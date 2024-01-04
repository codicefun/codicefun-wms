package com.codicefun.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.codicefun.wms.entity.po.Record;
import com.codicefun.wms.entity.vo.RecordVO;

public interface RecordMapper extends BaseMapper<Record> {

    IPage<RecordVO> selectVOPage(IPage<RecordVO> page);

}
