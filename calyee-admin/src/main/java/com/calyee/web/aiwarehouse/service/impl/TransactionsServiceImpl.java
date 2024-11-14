package com.calyee.web.aiwarehouse.service.impl;

import com.calyee.common.utils.DateUtils;
import com.calyee.web.aiwarehouse.domain.entity.Transactions;
import com.calyee.web.aiwarehouse.mapper.TransactionsMapper;
import com.calyee.web.aiwarehouse.service.ITransactionsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 出入库记录，记录每次库存变动Service业务层处理
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@Service
@AllArgsConstructor
public class TransactionsServiceImpl implements ITransactionsService {

    private TransactionsMapper transactionsMapper;

    /**
     * 查询出入库记录，记录每次库存变动
     *
     * @param transactionId 出入库记录，记录每次库存变动主键
     * @return 出入库记录，记录每次库存变动
     */
    @Override
    public Transactions selectTransactionsByTransactionId(Long transactionId) {
        return transactionsMapper.selectTransactionsByTransactionId(transactionId);
    }

    /**
     * 查询出入库记录，记录每次库存变动列表
     *
     * @param transactions 出入库记录，记录每次库存变动
     * @return 出入库记录，记录每次库存变动
     */
    @Override
    public List<Transactions> selectTransactionsList(Transactions transactions) {
        return transactionsMapper.selectTransactionsList(transactions);
    }

    /**
     * 新增出入库记录，记录每次库存变动
     *
     * @param transactions 出入库记录，记录每次库存变动
     * @return 结果
     */
    @Override
    public int insertTransactions(Transactions transactions) {
        transactions.setCreateTime(DateUtils.getNowDate());
        return transactionsMapper.insertTransactions(transactions);
    }

    /**
     * 修改出入库记录，记录每次库存变动
     *
     * @param transactions 出入库记录，记录每次库存变动
     * @return 结果
     */
    @Override
    public int updateTransactions(Transactions transactions) {
        return transactionsMapper.updateTransactions(transactions);
    }

    /**
     * 批量删除出入库记录，记录每次库存变动
     *
     * @param transactionIds 需要删除的出入库记录，记录每次库存变动主键
     * @return 结果
     */
    @Override
    public int deleteTransactionsByTransactionIds(Long[] transactionIds) {
        return transactionsMapper.deleteTransactionsByTransactionIds(transactionIds);
    }

    /**
     * 删除出入库记录，记录每次库存变动信息
     *
     * @param transactionId 出入库记录，记录每次库存变动主键
     * @return 结果
     */
    @Override
    public int deleteTransactionsByTransactionId(Long transactionId) {
        return transactionsMapper.deleteTransactionsByTransactionId(transactionId);
    }
}
