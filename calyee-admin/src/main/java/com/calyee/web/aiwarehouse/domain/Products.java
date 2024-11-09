package com.calyee.web.aiwarehouse.domain;

import com.calyee.common.annotation.Excel;
import com.calyee.common.core.domain.BaseEntity;
import lombok.*;

/**
 * 产品，存储产品信息对象 awm_products
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Products extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 产品唯一标识符
     */
    private Long productId;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String productName;

    /**
     * 产品描述
     */
    @Excel(name = "产品描述")
    private String description;

    /**
     * 库存单位eg: DP-992882
     */
    @Excel(name = "库存单位eg: DP-992882")
    private String sku;

    /**
     * 产品类别ID
     */
    @Excel(name = "产品类别ID")
    private Long categoryId;

    /**
     * 记录创建用户
     */
    @Excel(name = "记录创建用户")
    private String createUser;

    /**
     * 记录更新用户
     */
    @Excel(name = "记录更新用户")
    private String updateUser;

    /**
     * 删除标记，0表示未删除，1表示已删除
     */
    @Excel(name = "删除标记，0表示未删除，1表示已删除")
    private Long deleteFlag;

}
