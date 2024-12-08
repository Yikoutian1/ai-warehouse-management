package com.calyee.web.aiwarehouse.domain.vo;

import com.calyee.common.annotation.Excel;
import lombok.*;

/**
 * 产品，存储产品信息对象 awm_products
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductsVO{
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
     * 产品码eg: DP-992882
     */
    @Excel(name = "产品码eg: DP-992882")
    private String sku;

    /**
     * 图片（多个用逗号隔开）
     */
    @Excel(name = "图片")
    private String img;

    /**
     * 产品类别ID
     */
    @Excel(name = "产品类别ID")
    private Long categoryId;
}
