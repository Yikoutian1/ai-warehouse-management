package com.calyee.web.aiwarehouse.service;

import com.calyee.web.aiwarehouse.domain.Inventory;

import java.util.List;

/**
 * 库存，记录库存量及位置信息Service接口
 * 
 * @author CALYEE
 * @date 2024-11-09
 */
public interface IInventoryService 
{
    /**
     * 查询库存，记录库存量及位置信息
     * 
     * @param inventoryId 库存，记录库存量及位置信息主键
     * @return 库存，记录库存量及位置信息
     */
    public Inventory selectInventoryByInventoryId(Long inventoryId);

    /**
     * 查询库存，记录库存量及位置信息列表
     * 
     * @param inventory 库存，记录库存量及位置信息
     * @return 库存，记录库存量及位置信息集合
     */
    public List<Inventory> selectInventoryList(Inventory inventory);

    /**
     * 新增库存，记录库存量及位置信息
     * 
     * @param inventory 库存，记录库存量及位置信息
     * @return 结果
     */
    public int insertInventory(Inventory inventory);

    /**
     * 修改库存，记录库存量及位置信息
     * 
     * @param inventory 库存，记录库存量及位置信息
     * @return 结果
     */
    public int updateInventory(Inventory inventory);

    /**
     * 批量删除库存，记录库存量及位置信息
     * 
     * @param inventoryIds 需要删除的库存，记录库存量及位置信息主键集合
     * @return 结果
     */
    public int deleteInventoryByInventoryIds(Long[] inventoryIds);

    /**
     * 删除库存，记录库存量及位置信息信息
     * 
     * @param inventoryId 库存，记录库存量及位置信息主键
     * @return 结果
     */
    public int deleteInventoryByInventoryId(Long inventoryId);
}
