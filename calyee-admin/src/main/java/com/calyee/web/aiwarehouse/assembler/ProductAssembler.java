package com.calyee.web.aiwarehouse.assembler;


import com.calyee.web.aiwarehouse.domain.entity.Products;
import com.calyee.web.aiwarehouse.domain.vo.ProductsVO;

public class ProductAssembler {
    public static ProductsVO toVO(Products products) {
        return ProductsVO.builder()
                .productId(products.getProductId())
                .productName(products.getProductName())
                .description(products.getDescription())
                .sku(products.getSku())
                .img(products.getImg())
                .categoryId(products.getCategoryId())
                .build();
    }
}
