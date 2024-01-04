package com.codicefun.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codicefun.wms.entity.Constant;
import com.codicefun.wms.entity.po.Goods;
import com.codicefun.wms.entity.po.GoodsType;
import com.codicefun.wms.entity.po.Warehouse;
import com.codicefun.wms.entity.vo.AmountVO;
import com.codicefun.wms.entity.vo.GoodsVO;
import com.codicefun.wms.entity.vo.PaginationVO;
import com.codicefun.wms.entity.vo.ResponseVO;
import com.codicefun.wms.service.GoodsService;
import com.codicefun.wms.service.GoodsTypeService;
import com.codicefun.wms.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;
    private final WarehouseService warehouseService;
    private final GoodsTypeService goodsTypeService;

    public GoodsController(GoodsService goodsService,
                           WarehouseService warehouseService,
                           GoodsTypeService goodsTypeService) {
        this.goodsService = goodsService;
        this.warehouseService = warehouseService;
        this.goodsTypeService = goodsTypeService;
    }

    @PostMapping
    public ResponseVO<Goods> save(@RequestBody GoodsVO goodsVO) {
        Goods goods = vo2po(goodsVO);

        return goodsService.save(goods) ? ResponseVO.success() : ResponseVO.fail();
    }

    @GetMapping
    public ResponseVO<PaginationVO<GoodsVO>> list(
            @RequestParam(required = false, defaultValue = Constant.PAGE_CURRENT) Long current,
            @RequestParam(required = false, defaultValue = Constant.PAGE_SIZE) Long size) {
        IPage<GoodsVO> page = new Page<>(current, size);
        IPage<GoodsVO> result = goodsService.pageVO(page);

        return ResponseVO.page(result);
    }

    @GetMapping("/{id}")
    public ResponseVO<GoodsVO> get(@PathVariable Long id) {
        GoodsVO goodsVO = goodsService.getVOById(id);

        return ResponseVO.success(goodsVO);
    }

    @PutMapping("/{id}")
    public ResponseVO<Goods> update(@RequestBody GoodsVO goodsVO) {
        Goods goods = vo2po(goodsVO);

        return goodsService.updateById(goods) ? ResponseVO.success() : ResponseVO.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseVO<Goods> remove(@PathVariable int id) {
        return goodsService.removeById(id) ? ResponseVO.success() : ResponseVO.fail();
    }

    @PutMapping("/{id}/amount")
    public ResponseVO<Goods> changeAmount(@PathVariable Long id, @RequestBody AmountVO amountVO) {
        Goods goods = goodsService.getById(id);
        goods.setAmount(amountVO.getAmount());

        return goodsService.updateById(goods) ? ResponseVO.success() : ResponseVO.fail();
    }

    private Goods vo2po(GoodsVO goodsVO) {
        String warehouseName = goodsVO.getWarehouse();
        String typeName = goodsVO.getType();

        LambdaQueryWrapper<Warehouse> warehouseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<GoodsType> goodsTypeLambdaQueryWrapper = new LambdaQueryWrapper<>();

        warehouseLambdaQueryWrapper.eq(Warehouse::getName, warehouseName);
        goodsTypeLambdaQueryWrapper.eq(GoodsType::getName, typeName);

        Warehouse warehouse = warehouseService.getOne(warehouseLambdaQueryWrapper);
        GoodsType type = goodsTypeService.getOne(goodsTypeLambdaQueryWrapper);

        return new Goods(goodsVO.getId(), goodsVO.getName(), warehouse.getId(), type.getId(), goodsVO.getAmount(), goodsVO.getDescription());
    }

}
