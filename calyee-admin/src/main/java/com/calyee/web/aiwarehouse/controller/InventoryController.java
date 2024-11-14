package com.calyee.web.aiwarehouse.controller;

import com.calyee.common.annotation.Log;
import com.calyee.common.core.controller.BaseController;
import com.calyee.common.core.domain.AjaxResult;
import com.calyee.common.core.page.TableDataInfo;
import com.calyee.common.enums.BusinessType;
import com.calyee.common.utils.poi.ExcelUtil;
import com.calyee.web.aiwarehouse.domain.entity.Inventory;
import com.calyee.web.aiwarehouse.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 库存，记录库存量及位置信息Controller
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@RestController
@RequestMapping("/system/inventory")
public class InventoryController extends BaseController {
    @Autowired
    private IInventoryService inventoryService;

    /**
     * 查询库存，记录库存量及位置信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Inventory inventory) {
        startPage();
        List<Inventory> list = inventoryService.selectInventoryList(inventory);
        return getDataTable(list);
    }

    /**
     * 导出库存，记录库存量及位置信息列表
     */
    @Log(title = "库存，记录库存量及位置信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Inventory inventory) {
        List<Inventory> list = inventoryService.selectInventoryList(inventory);
        ExcelUtil<Inventory> util = new ExcelUtil<Inventory>(Inventory.class);
        util.exportExcel(response, list, "库存，记录库存量及位置信息数据");
    }

    /**
     * 获取库存，记录库存量及位置信息详细信息
     */
    @GetMapping(value = "/{inventoryId}")
    public AjaxResult getInfo(@PathVariable("inventoryId") Long inventoryId) {
        return success(inventoryService.selectInventoryByInventoryId(inventoryId));
    }

    /**
     * 新增库存，记录库存量及位置信息
     */
    @Log(title = "库存，记录库存量及位置信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Inventory inventory) {
        return toAjax(inventoryService.insertInventory(inventory));
    }

    /**
     * 修改库存，记录库存量及位置信息
     */
    @Log(title = "库存，记录库存量及位置信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Inventory inventory) {
        return toAjax(inventoryService.updateInventory(inventory));
    }

    /**
     * 删除库存，记录库存量及位置信息
     */
    @Log(title = "库存，记录库存量及位置信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{inventoryIds}")
    public AjaxResult remove(@PathVariable Long[] inventoryIds) {
        return toAjax(inventoryService.deleteInventoryByInventoryIds(inventoryIds));
    }
}
