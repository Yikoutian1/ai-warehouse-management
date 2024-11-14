package com.calyee.web.aiwarehouse.mapper;

import com.calyee.web.aiwarehouse.domain.entity.Products;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 产品，存储产品信息Mapper接口
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@Mapper
public interface ProductsMapper {
    /**
     * 查询产品，存储产品信息
     *
     * @param productId 产品，存储产品信息主键
     * @return 产品，存储产品信息
     */
    public Products selectProductsByProductId(Long productId);

    /**
     * 查询产品，存储产品信息列表
     *
     * @param products 产品，存储产品信息
     * @return 产品，存储产品信息集合
     */
    public List<Products> selectProductsList(Products products);

    /**
     * 新增产品，存储产品信息
     *
     * @param products 产品，存储产品信息
     * @return 结果
     */
    public int insertProducts(Products products);

    /**
     * 修改产品，存储产品信息
     *
     * @param products 产品，存储产品信息
     * @return 结果
     */
    public int updateProducts(Products products);

    /**
     * 删除产品，存储产品信息
     *
     * @param productId 产品，存储产品信息主键
     * @return 结果
     */
    public int deleteProductsByProductId(Long productId);

    /**
     * 批量删除产品，存储产品信息
     *
     * @param productIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductsByProductIds(Long[] productIds);
}
