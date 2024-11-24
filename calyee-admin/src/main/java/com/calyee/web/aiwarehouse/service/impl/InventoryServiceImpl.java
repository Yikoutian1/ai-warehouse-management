package com.calyee.web.aiwarehouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.calyee.common.utils.DateUtils;
import com.calyee.web.aiwarehouse.domain.entity.Inventory;
import com.calyee.web.aiwarehouse.mapper.InventoryMapper;
import com.calyee.web.aiwarehouse.service.IInventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 库存，记录库存量及位置信息Service业务层处理
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@Service
@AllArgsConstructor
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements IInventoryService {

    private InventoryMapper inventoryMapper;

    /**
     * 查询库存，记录库存量及位置信息
     *
     * @param inventoryId 库存，记录库存量及位置信息主键
     * @return 库存，记录库存量及位置信息
     */
    @Override
    public Inventory selectInventoryByInventoryId(Long inventoryId) {
        return inventoryMapper.selectInventoryByInventoryId(inventoryId);
    }

    /**
     * 查询库存，记录库存量及位置信息列表
     *
     * @param inventory 库存，记录库存量及位置信息
     * @return 库存，记录库存量及位置信息
     */
    @Override
    public List<Inventory> selectInventoryList(Inventory inventory) {
        return inventoryMapper.selectInventoryList(inventory);
    }

    /**
     * 新增库存，记录库存量及位置信息
     *
     * @param inventory 库存，记录库存量及位置信息
     * @return 结果
     */
    @Override
    public int insertInventory(Inventory inventory) {
        inventory.setCreateTime(DateUtils.getNowDate());
        return inventoryMapper.insertInventory(inventory);
    }

    /**
     * 修改库存，记录库存量及位置信息
     *
     * @param inventory 库存，记录库存量及位置信息
     * @return 结果
     */
    @Override
    public int updateInventory(Inventory inventory) {
        inventory.setUpdateTime(DateUtils.getNowDate());
        return inventoryMapper.updateInventory(inventory);
    }

    /**
     * 批量删除库存，记录库存量及位置信息
     *
     * @param inventoryIds 需要删除的库存，记录库存量及位置信息主键
     * @return 结果
     */
    @Override
    public int deleteInventoryByInventoryIds(Long[] inventoryIds) {
        return inventoryMapper.deleteInventoryByInventoryIds(inventoryIds);
    }

    /**
     * 删除库存，记录库存量及位置信息信息
     *
     * @param inventoryId 库存，记录库存量及位置信息主键
     * @return 结果
     */
    @Override
    public int deleteInventoryByInventoryId(Long inventoryId) {
        return inventoryMapper.deleteInventoryByInventoryId(inventoryId);
    }
}
