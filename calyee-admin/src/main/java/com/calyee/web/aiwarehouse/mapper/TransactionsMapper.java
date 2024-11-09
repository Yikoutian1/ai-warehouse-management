package com.calyee.web.aiwarehouse.mapper;

import com.calyee.web.aiwarehouse.domain.Transactions;

import java.util.List;

/**
 * 出入库记录，记录每次库存变动Mapper接口
 *
 * @author CALYEE
 * @date 2024-11-09
 */
public interface TransactionsMapper {
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
     * 删除出入库记录，记录每次库存变动
     *
     * @param transactionId 出入库记录，记录每次库存变动主键
     * @return 结果
     */
    public int deleteTransactionsByTransactionId(Long transactionId);

    /**
     * 批量删除出入库记录，记录每次库存变动
     *
     * @param transactionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTransactionsByTransactionIds(Long[] transactionIds);
}
