package com.codicefun.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codicefun.wms.entity.Constant;
import com.codicefun.wms.entity.po.GoodsType;
import com.codicefun.wms.entity.vo.PaginationVO;
import com.codicefun.wms.entity.vo.ResponseVO;
import com.codicefun.wms.service.GoodsTypeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods/type")
public class GoodsTypeController {

    private final GoodsTypeService goodsTypeService;

    public GoodsTypeController(GoodsTypeService goodsTypeService) {
        this.goodsTypeService = goodsTypeService;
    }

    @PostMapping
    public ResponseVO<GoodsType> save(@RequestBody GoodsType goodsType) {
        return goodsTypeService.save(goodsType) ? ResponseVO.success() : ResponseVO.fail();
    }

    @GetMapping
    public ResponseVO<PaginationVO<GoodsType>> list(
            @RequestParam(required = false, defaultValue = Constant.PAGE_CURRENT) Long current,
            @RequestParam(required = false, defaultValue = Constant.PAGE_SIZE) Long size,
            GoodsType goodsType) {
        IPage<GoodsType> page = new Page<>(current, size);
        LambdaQueryWrapper<GoodsType> queryWrapper = new LambdaQueryWrapper<>();

        if (goodsType != null) {
            if (goodsType.getName() != null) {
                queryWrapper.like(GoodsType::getName, goodsType.getName());
            }
        }

        IPage<GoodsType> result = goodsTypeService.page(page, queryWrapper);

        return ResponseVO.page(result);
    }

    @GetMapping("/{id}")
    public ResponseVO<GoodsType> get(@PathVariable Long id) {
        GoodsType user = goodsTypeService.getById(id);

        return ResponseVO.success(user);
    }

    @PutMapping("/{id}")
    public ResponseVO<GoodsType> update(@RequestBody GoodsType goodsType) {
        return goodsTypeService.updateById(goodsType) ? ResponseVO.success() : ResponseVO.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseVO<GoodsType> remove(@PathVariable int id) {
        return goodsTypeService.removeById(id) ? ResponseVO.success() : ResponseVO.fail();
    }

}
