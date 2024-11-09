package com.calyee.web.aiwarehouse.domain;

import com.calyee.common.annotation.Excel;
import com.calyee.common.core.domain.BaseEntity;
import lombok.*;

/**
 * 库存，记录库存量及位置信息对象 awm_inventory
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 库存记录唯一标识符
     */
    private Long inventoryId;

    /**
     * 关联的库位ID（locations.location_id）
     */
    @Excel(name = "关联的库位ID（locations.location_id）")
    private Long locationId;

    /**
     * 关联的产品ID（products.product_id）
     */
    @Excel(name = "关联的产品ID（products.product_id）")
    private Long productId;

    /**
     * 库存数量
     */
    @Excel(name = "库存数量")
    private Long quantity;

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
