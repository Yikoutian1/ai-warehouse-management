package com.calyee.web.aiwarehouse.controller;

import com.calyee.common.annotation.Log;
import com.calyee.common.core.controller.BaseController;
import com.calyee.common.core.domain.AjaxResult;
import com.calyee.common.core.page.TableDataInfo;
import com.calyee.common.enums.BusinessType;
import com.calyee.common.utils.SecurityUtils;
import com.calyee.common.utils.poi.ExcelUtil;
import com.calyee.web.aiwarehouse.domain.entity.Products;
import com.calyee.web.aiwarehouse.service.IProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 产品，存储产品信息Controller
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@RestController
@RequestMapping("/system/products")
public class ProductsController extends BaseController {
    @Autowired
    private IProductsService productsService;

    /**
     * 查询产品，存储产品信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Products products) {
        startPage();
        List<Products> list = productsService.selectProductsList(products);
        return getDataTable(list);
    }

    /**
     * 导出产品，存储产品信息列表
     */
    @Log(title = "产品，存储产品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Products products) {
        List<Products> list = productsService.selectProductsList(products);
        ExcelUtil<Products> util = new ExcelUtil<Products>(Products.class);
        util.exportExcel(response, list, "产品，存储产品信息数据");
    }

    /**
     * 获取产品，存储产品信息详细信息
     */
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") Long productId) {
        return success(productsService.selectProductsByProductId(productId));
    }

    /**
     * 新增产品，存储产品信息
     */
    @Log(title = "产品，存储产品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Products products) {
        String userId = SecurityUtils.getUserStringId();
        products.setUpdateUser(userId);
        products.setCreateUser(userId);
        return toAjax(productsService.insertProducts(products));
    }

    /**
     * 修改产品，存储产品信息
     */
    @Log(title = "产品，存储产品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Products products) {
        String userId = SecurityUtils.getUserStringId();
        products.setUpdateUser(userId);
        return toAjax(productsService.updateProducts(products));
    }

    /**
     * 删除产品，存储产品信息
     */
    @Log(title = "产品，存储产品信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable List<String> productIds) {
        return toAjax(productsService.deleteProductsByProductIds(productIds));
    }
}
