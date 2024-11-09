package com.calyee.web.aiwarehouse.controller;

import com.calyee.common.annotation.Log;
import com.calyee.common.core.controller.BaseController;
import com.calyee.common.core.domain.AjaxResult;
import com.calyee.common.core.page.TableDataInfo;
import com.calyee.common.enums.BusinessType;
import com.calyee.common.utils.poi.ExcelUtil;
import com.calyee.web.aiwarehouse.domain.Locations;
import com.calyee.web.aiwarehouse.service.ILocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 库位，记录仓库中每个具体存储位置的详细信息Controller
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@RestController
@RequestMapping("/system/locations")
public class LocationsController extends BaseController {
    @Autowired
    private ILocationsService locationsService;

    /**
     * 查询库位，记录仓库中每个具体存储位置的详细信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:locations:list')")
    @GetMapping("/list")
    public TableDataInfo list(Locations locations) {
        startPage();
        List<Locations> list = locationsService.selectLocationsList(locations);
        return getDataTable(list);
    }

    /**
     * 导出库位，记录仓库中每个具体存储位置的详细信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:locations:export')")
    @Log(title = "库位，记录仓库中每个具体存储位置的详细信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Locations locations) {
        List<Locations> list = locationsService.selectLocationsList(locations);
        ExcelUtil<Locations> util = new ExcelUtil<Locations>(Locations.class);
        util.exportExcel(response, list, "库位，记录仓库中每个具体存储位置的详细信息数据");
    }

    /**
     * 获取库位，记录仓库中每个具体存储位置的详细信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:locations:query')")
    @GetMapping(value = "/{locationId}")
    public AjaxResult getInfo(@PathVariable("locationId") Long locationId) {
        return success(locationsService.selectLocationsByLocationId(locationId));
    }

    /**
     * 新增库位，记录仓库中每个具体存储位置的详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:locations:add')")
    @Log(title = "库位，记录仓库中每个具体存储位置的详细信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Locations locations) {
        return toAjax(locationsService.insertLocations(locations));
    }

    /**
     * 修改库位，记录仓库中每个具体存储位置的详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:locations:edit')")
    @Log(title = "库位，记录仓库中每个具体存储位置的详细信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Locations locations) {
        return toAjax(locationsService.updateLocations(locations));
    }

    /**
     * 删除库位，记录仓库中每个具体存储位置的详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:locations:remove')")
    @Log(title = "库位，记录仓库中每个具体存储位置的详细信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{locationIds}")
    public AjaxResult remove(@PathVariable Long[] locationIds) {
        return toAjax(locationsService.deleteLocationsByLocationIds(locationIds));
    }
}
