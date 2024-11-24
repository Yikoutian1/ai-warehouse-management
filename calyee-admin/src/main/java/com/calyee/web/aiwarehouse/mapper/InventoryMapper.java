package com.calyee.web.aiwarehouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.calyee.web.aiwarehouse.domain.entity.Inventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 库存，记录库存量及位置信息Mapper接口
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {
    /**
     * 查询库存，记录库存量及位置信息
     *
     * @param inventoryId 库存，记录库存量及位置信息主键
     * @return 库存，记录库存量及位置信息
     */
    Inventory selectInventoryByInventoryId(Long inventoryId);

    /**
     * 查询库存，记录库存量及位置信息列表
     *
     * @param inventory 库存，记录库存量及位置信息
     * @return 库存，记录库存量及位置信息集合
     */
    List<Inventory> selectInventoryList(Inventory inventory);

    /**
     * 新增库存，记录库存量及位置信息
     *
     * @param inventory 库存，记录库存量及位置信息
     * @return 结果
     */
    int insertInventory(Inventory inventory);

    /**
     * 修改库存，记录库存量及位置信息
     *
     * @param inventory 库存，记录库存量及位置信息
     * @return 结果
     */
    int updateInventory(Inventory inventory);

    /**
     * 删除库存，记录库存量及位置信息
     *
     * @param inventoryId 库存，记录库存量及位置信息主键
     * @return 结果
     */
    int deleteInventoryByInventoryId(Long inventoryId);

    /**
     * 批量删除库存，记录库存量及位置信息
     *
     * @param inventoryIds 需要删除的数据主键集合
     * @return 结果
     */
    int deleteInventoryByInventoryIds(Long[] inventoryIds);
}
