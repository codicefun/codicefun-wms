package com.codicefun.wms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codicefun.wms.entity.Constant;
import com.codicefun.wms.entity.po.User;
import com.codicefun.wms.entity.po.Warehouse;
import com.codicefun.wms.entity.vo.PaginationVO;
import com.codicefun.wms.entity.vo.ResponseVO;
import com.codicefun.wms.service.WarehouseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public ResponseVO<Warehouse> save(@RequestBody Warehouse warehouse) {
        return warehouseService.save(warehouse) ? ResponseVO.success() : ResponseVO.fail();
    }

    @GetMapping
    public ResponseVO<PaginationVO<Warehouse>> list(
            @RequestParam(required = false, defaultValue = Constant.PAGE_CURRENT) Long current,
            @RequestParam(required = false, defaultValue = Constant.PAGE_SIZE) Long size,
            Warehouse warehouse) {
        IPage<Warehouse> page = new Page<>(current, size);
        LambdaQueryWrapper<Warehouse> queryWrapper = new LambdaQueryWrapper<>();

        if (warehouse != null) {
            if (warehouse.getName() != null) {
                queryWrapper.like(Warehouse::getName, warehouse.getName());
            }
        }

        IPage<Warehouse> result = warehouseService.page(page, queryWrapper);

        return ResponseVO.page(result);
    }

    @GetMapping("/{id}")
    public ResponseVO<Warehouse> get(@PathVariable Long id) {
        Warehouse user = warehouseService.getById(id);

        return ResponseVO.success(user);
    }

    @PutMapping("/{id}")
    public ResponseVO<Warehouse> update(@RequestBody Warehouse warehouse) {
        return warehouseService.updateById(warehouse) ? ResponseVO.success() : ResponseVO.fail();
    }

    @DeleteMapping("/{id}")
    public ResponseVO<User> remove(@PathVariable int id) {
        return warehouseService.removeById(id) ? ResponseVO.success() : ResponseVO.fail();
    }

}
