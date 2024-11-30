package com.calyee.web.aiwarehouse.controller;

import com.calyee.common.annotation.Log;
import com.calyee.common.core.controller.BaseController;
import com.calyee.common.core.domain.AjaxResult;
import com.calyee.common.core.page.TableDataInfo;
import com.calyee.common.enums.BusinessType;
import com.calyee.common.utils.poi.ExcelUtil;
import com.calyee.web.aiwarehouse.domain.entity.Transactions;
import com.calyee.web.aiwarehouse.service.ITransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 出入库记录，记录每次库存变动Controller
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@RestController
@RequestMapping("/system/transactions")
public class TransactionsController extends BaseController {
    @Autowired
    private ITransactionsService transactionsService;

    /**
     * 查询出入库记录，记录每次库存变动列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Transactions transactions) {
        startPage();
        List<Transactions> list = transactionsService.selectTransactionsList(transactions);
        return getDataTable(list);
    }

    /**
     * 导出出入库记录，记录每次库存变动列表
     */
    @Log(title = "出入库记录，记录每次库存变动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Transactions transactions) {
        List<Transactions> list = transactionsService.selectTransactionsList(transactions);
        ExcelUtil<Transactions> util = new ExcelUtil<Transactions>(Transactions.class);
        util.exportExcel(response, list, "出入库记录，记录每次库存变动数据");
    }

    /**
     * 获取出入库记录，记录每次库存变动详细信息
     */
    @GetMapping(value = "/{transactionId}")
    public AjaxResult getInfo(@PathVariable("transactionId") Long transactionId) {
        return success(transactionsService.selectTransactionsByTransactionId(transactionId));
    }

    /**
     * 新增出入库记录，记录每次库存变动
     */
    @Log(title = "出入库记录，记录每次库存变动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Transactions transactions) {
        return toAjax(transactionsService.insertTransactions(transactions));
    }

    /**
     * 修改出入库记录，记录每次库存变动
     */
    @Log(title = "出入库记录，记录每次库存变动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Transactions transactions) {
        return toAjax(transactionsService.updateTransactions(transactions));
    }

    /**
     * 删除出入库记录，记录每次库存变动
     */
    @Log(title = "出入库记录，记录每次库存变动", businessType = BusinessType.DELETE)
    @DeleteMapping("/{transactionIds}")
    public AjaxResult remove(@PathVariable List<String> transactionIds) {
        return toAjax(transactionsService.deleteTransactionsByTransactionIds(transactionIds));
    }
}
