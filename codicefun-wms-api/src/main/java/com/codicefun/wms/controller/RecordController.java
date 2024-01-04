package com.codicefun.wms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codicefun.wms.entity.Constant;
import com.codicefun.wms.entity.po.Record;
import com.codicefun.wms.entity.vo.PaginationVO;
import com.codicefun.wms.entity.vo.RecordVO;
import com.codicefun.wms.entity.vo.ResponseVO;
import com.codicefun.wms.service.GoodsService;
import com.codicefun.wms.service.GoodsTypeService;
import com.codicefun.wms.service.RecordService;
import com.codicefun.wms.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
public class RecordController {

    private final RecordService recordService;
    private final GoodsService goodsService;
    private final WarehouseService warehouseService;
    private final GoodsTypeService goodsTypeService;

    public RecordController(RecordService recordService,
                            GoodsService goodsService,
                            WarehouseService warehouseService,
                            GoodsTypeService goodsTypeService) {
        this.recordService = recordService;
        this.goodsService = goodsService;
        this.warehouseService = warehouseService;
        this.goodsTypeService = goodsTypeService;
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

    // @DeleteMapping("/{id}")
    // public ResponseVO<Object> remove(@PathVariable Long id) {
    //     Record record = recordService.getById(id);
    //     if (recordService.removeById(id)) {
    //         String goodsName = goodsService.getNameById(record.getGoodsId());
    //         String operation = record.getOperation();
    //         Integer amount = record.getAmount();
    //
    //         if (operation.equals("出库")) {
    //
    //         }
    //
    //     } else {
    //         return ResponseVO.fail();
    //     }
    // }

    private RecordVO po2vo(Record record) {
        return null;
    }

}
