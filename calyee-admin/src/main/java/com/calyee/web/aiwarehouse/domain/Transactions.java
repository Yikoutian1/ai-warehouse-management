package com.calyee.web.aiwarehouse.domain;

import com.calyee.common.annotation.Excel;
import com.calyee.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * 出入库记录，记录每次库存变动对象 awm_transactions
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transactions extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 交易记录唯一标识符
     */
    private Long transactionId;

    /**
     * 关联的库存记录ID（inventory.inventory_id）
     */
    @Excel(name = "关联的库存记录ID（inventory.inventory_id）")
    private Long inventoryId;

    /**
     * 交易类型，IN表示入库，OUT表示出库
     */
    @Excel(name = "交易类型，IN表示入库，OUT表示出库")
    private String transactionType;

    /**
     * 交易数量
     */
    @Excel(name = "交易数量")
    private Long quantity;

    /**
     * 交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transactionTime;

    /**
     * 记录创建用户
     */
    @Excel(name = "记录创建用户")
    private String createUser;

    /**
     * 删除标记，0表示未删除，1表示已删除
     */
    @Excel(name = "删除标记，0表示未删除，1表示已删除")
    private Long deleteFlag;

}
