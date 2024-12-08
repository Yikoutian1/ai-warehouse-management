package com.calyee.web.aiwarehouse.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.calyee.web.aiwarehouse.domain.entity.Locations;
import com.calyee.web.aiwarehouse.domain.vo.ProductsVO;

import java.util.List;

/**
 * 库位，记录仓库中每个具体存储位置的详细信息Service接口
 *
 * @author CALYEE
 * @date 2024-11-09
 */
public interface ILocationsService extends IService<Locations> {
    /**
     * 查询库位，记录仓库中每个具体存储位置的详细信息
     *
     * @param locationId 库位，记录仓库中每个具体存储位置的详细信息主键
     * @return 库位，记录仓库中每个具体存储位置的详细信息
     */
    Locations selectLocationsByLocationId(Long locationId);

    /**
     * 查询库位，记录仓库中每个具体存储位置的详细信息列表
     *
     * @param locations 库位，记录仓库中每个具体存储位置的详细信息
     * @return 库位，记录仓库中每个具体存储位置的详细信息集合
     */
    List<Locations> selectLocationsList(Locations locations);

    /**
     * 新增库位，记录仓库中每个具体存储位置的详细信息
     *
     * @param locations 库位，记录仓库中每个具体存储位置的详细信息
     * @return 结果
     */
    int insertLocations(Locations locations);

    /**
     * 修改库位，记录仓库中每个具体存储位置的详细信息
     *
     * @param locations 库位，记录仓库中每个具体存储位置的详细信息
     * @return 结果
     */
    int updateLocations(Locations locations);

    /**
     * 批量删除库位，记录仓库中每个具体存储位置的详细信息
     *
     * @param locationIds 需要删除的库位，记录仓库中每个具体存储位置的详细信息主键集合
     * @return 结果
     */
    int deleteLocationsByLocationIds(List<String> locationIds);

    /**
     * 删除库位，记录仓库中每个具体存储位置的详细信息信息
     *
     * @param locationId 库位，记录仓库中每个具体存储位置的详细信息主键
     * @return 结果
     */
    int deleteLocationsByLocationId(Long locationId);

    ProductsVO product(String locationIds);
}
