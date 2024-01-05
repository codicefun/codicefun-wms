package com.codicefun.wms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codicefun.wms.entity.Constant;
import com.codicefun.wms.entity.po.Goods;
import com.codicefun.wms.entity.po.Record;
import com.codicefun.wms.entity.vo.PaginationVO;
import com.codicefun.wms.entity.vo.RecordVO;
import com.codicefun.wms.entity.vo.ResponseVO;
import com.codicefun.wms.service.GoodsService;
import com.codicefun.wms.service.RecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
public class RecordController {

    private final RecordService recordService;
    private final GoodsService goodsService;

    public RecordController(RecordService recordService,
                            GoodsService goodsService) {
        this.recordService = recordService;
        this.goodsService = goodsService;
    }

    @PostMapping
    public ResponseVO<Object> save(@RequestBody Record record) {
        return recordService.save(record) ? ResponseVO.success() : ResponseVO.fail();
    }

    @GetMapping
    public ResponseVO<PaginationVO<RecordVO>> list(
            @RequestParam(required = false, defaultValue = Constant.PAGE_CURRENT) Long current,
            @RequestParam(required = false, defaultValue = Constant.PAGE_SIZE) Long size) {
        IPage<RecordVO> page = recordService.pageVO(new Page<>(current, size));

        return ResponseVO.page(page);
    }

    @DeleteMapping("/{id}")
    public ResponseVO<Object> recover(@PathVariable Integer id) {
        Record record = recordService.getById(id);

        if (recordService.removeById(id)) {
            String operation = record.getOperation();
            Integer amount = record.getAmount();
            Goods goods = goodsService.getById(record.getGoodsId());
            Integer currentAmount = goods.getAmount();

            if (operation.equals("出库")) {
                goods.setAmount(currentAmount + amount);
            } else {
                goods.setAmount(currentAmount - amount);
            }
            goodsService.updateById(goods);
            return ResponseVO.success();
        } else {
            return ResponseVO.fail();
        }
    }

}
