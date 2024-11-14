package com.calyee.web.aiwarehouse.service;

import com.calyee.web.aiwarehouse.domain.entity.Locations;

import java.util.List;

/**
 * 库位，记录仓库中每个具体存储位置的详细信息Service接口
 *
 * @author CALYEE
 * @date 2024-11-09
 */
public interface ILocationsService {
    /**
     * 查询库位，记录仓库中每个具体存储位置的详细信息
     *
     * @param locationId 库位，记录仓库中每个具体存储位置的详细信息主键
     * @return 库位，记录仓库中每个具体存储位置的详细信息
     */
    public Locations selectLocationsByLocationId(Long locationId);

    /**
     * 查询库位，记录仓库中每个具体存储位置的详细信息列表
     *
     * @param locations 库位，记录仓库中每个具体存储位置的详细信息
     * @return 库位，记录仓库中每个具体存储位置的详细信息集合
     */
    public List<Locations> selectLocationsList(Locations locations);

    /**
     * 新增库位，记录仓库中每个具体存储位置的详细信息
     *
     * @param locations 库位，记录仓库中每个具体存储位置的详细信息
     * @return 结果
     */
    public int insertLocations(Locations locations);

    /**
     * 修改库位，记录仓库中每个具体存储位置的详细信息
     *
     * @param locations 库位，记录仓库中每个具体存储位置的详细信息
     * @return 结果
     */
    public int updateLocations(Locations locations);

    /**
     * 批量删除库位，记录仓库中每个具体存储位置的详细信息
     *
     * @param locationIds 需要删除的库位，记录仓库中每个具体存储位置的详细信息主键集合
     * @return 结果
     */
    public int deleteLocationsByLocationIds(Long[] locationIds);

    /**
     * 删除库位，记录仓库中每个具体存储位置的详细信息信息
     *
     * @param locationId 库位，记录仓库中每个具体存储位置的详细信息主键
     * @return 结果
     */
    public int deleteLocationsByLocationId(Long locationId);
}
