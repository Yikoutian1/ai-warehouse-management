package com.calyee.web.aiwarehouse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.calyee.web.aiwarehouse.domain.entity.Transactions;

import java.util.List;

/**
 * 出入库记录，记录每次库存变动Service接口
 *
 * @author CALYEE
 * @date 2024-11-09
 */
public interface ITransactionsService extends IService<Transactions> {
    /**
     * 查询出入库记录，记录每次库存变动
     *
     * @param transactionId 出入库记录，记录每次库存变动主键
     * @return 出入库记录，记录每次库存变动
     */
    public Transactions selectTransactionsByTransactionId(Long transactionId);

    /**
     * 查询出入库记录，记录每次库存变动列表
     *
     * @param transactions 出入库记录，记录每次库存变动
     * @return 出入库记录，记录每次库存变动集合
     */
    public List<Transactions> selectTransactionsList(Transactions transactions);

    /**
     * 新增出入库记录，记录每次库存变动
     *
     * @param transactions 出入库记录，记录每次库存变动
     * @return 结果
     */
    public int insertTransactions(Transactions transactions);

    /**
     * 修改出入库记录，记录每次库存变动
     *
     * @param transactions 出入库记录，记录每次库存变动
     * @return 结果
     */
    public int updateTransactions(Transactions transactions);

    /**
     * 批量删除出入库记录，记录每次库存变动
     *
     * @param transactionIds 需要删除的出入库记录，记录每次库存变动主键集合
     * @return 结果
     */
    public int deleteTransactionsByTransactionIds(List<String> transactionIds);

    /**
     * 删除出入库记录，记录每次库存变动信息
     *
     * @param transactionId 出入库记录，记录每次库存变动主键
     * @return 结果
     */
    public int deleteTransactionsByTransactionId(Long transactionId);
}
