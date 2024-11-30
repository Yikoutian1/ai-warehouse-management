package com.calyee.web.aiwarehouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.calyee.common.utils.DateUtils;
import com.calyee.web.aiwarehouse.domain.entity.Products;
import com.calyee.web.aiwarehouse.mapper.ProductsMapper;
import com.calyee.web.aiwarehouse.service.IProductsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品，存储产品信息Service业务层处理
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@Service
@AllArgsConstructor
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements IProductsService{
    private ProductsMapper productsMapper;

    /**
     * 查询产品，存储产品信息
     *
     * @param productId 产品，存储产品信息主键
     * @return 产品，存储产品信息
     */
    @Override
    public Products selectProductsByProductId(Long productId) {
        return productsMapper.selectProductsByProductId(productId);
    }

    /**
     * 查询产品，存储产品信息列表
     *
     * @param products 产品，存储产品信息
     * @return 产品，存储产品信息
     */
    @Override
    public List<Products> selectProductsList(Products products) {
        return productsMapper.selectProductsList(products);
    }

    /**
     * 新增产品，存储产品信息
     *
     * @param products 产品，存储产品信息
     * @return 结果
     */
    @Override
    public int insertProducts(Products products) {
        products.setCreateTime(DateUtils.getNowDate());
        return productsMapper.insertProducts(products);
    }

    /**
     * 修改产品，存储产品信息
     *
     * @param products 产品，存储产品信息
     * @return 结果
     */
    @Override
    public int updateProducts(Products products) {
        products.setUpdateTime(DateUtils.getNowDate());
        return productsMapper.updateProducts(products);
    }

    /**
     * 批量删除产品，存储产品信息
     *
     * @param productIds 需要删除的产品，存储产品信息主键
     * @return 结果
     */
    @Override
    public int deleteProductsByProductIds(List<String> productIds) {
        return productsMapper.deleteProductsByProductIds(productIds);
    }

    /**
     * 删除产品，存储产品信息信息
     *
     * @param productId 产品，存储产品信息主键
     * @return 结果
     */
    @Override
    public int deleteProductsByProductId(Long productId) {
        return productsMapper.deleteProductsByProductId(productId);
    }
}
