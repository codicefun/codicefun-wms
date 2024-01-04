package com.codicefun.wms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codicefun.wms.entity.po.Record;
import com.codicefun.wms.entity.vo.RecordVO;

public interface RecordService extends IService<Record> {

    IPage<RecordVO> pageVO(IPage<RecordVO> page);

}
