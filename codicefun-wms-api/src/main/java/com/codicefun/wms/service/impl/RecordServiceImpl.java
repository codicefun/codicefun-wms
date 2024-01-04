package com.codicefun.wms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codicefun.wms.entity.po.Record;
import com.codicefun.wms.entity.vo.RecordVO;
import com.codicefun.wms.mapper.RecordMapper;
import com.codicefun.wms.service.RecordService;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

    @Override
    public IPage<RecordVO> pageVO(IPage<RecordVO> page) {
        return baseMapper.selectVOPage(page);
    }

}
