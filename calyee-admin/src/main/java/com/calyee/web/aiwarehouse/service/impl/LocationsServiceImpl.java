package com.calyee.web.aiwarehouse.service.impl;

import com.calyee.common.utils.DateUtils;
import com.calyee.web.aiwarehouse.domain.Locations;
import com.calyee.web.aiwarehouse.mapper.LocationsMapper;
import com.calyee.web.aiwarehouse.service.ILocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 库位，记录仓库中每个具体存储位置的详细信息Service业务层处理
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@Service
public class LocationsServiceImpl implements ILocationsService {
    @Autowired
    private LocationsMapper locationsMapper;

    /**
     * 查询库位，记录仓库中每个具体存储位置的详细信息
     *
     * @param locationId 库位，记录仓库中每个具体存储位置的详细信息主键
     * @return 库位，记录仓库中每个具体存储位置的详细信息
     */
    @Override
    public Locations selectLocationsByLocationId(Long locationId) {
        return locationsMapper.selectLocationsByLocationId(locationId);
    }

    /**
     * 查询库位，记录仓库中每个具体存储位置的详细信息列表
     *
     * @param locations 库位，记录仓库中每个具体存储位置的详细信息
     * @return 库位，记录仓库中每个具体存储位置的详细信息
     */
    @Override
    public List<Locations> selectLocationsList(Locations locations) {
        return locationsMapper.selectLocationsList(locations);
    }

    /**
     * 新增库位，记录仓库中每个具体存储位置的详细信息
     *
     * @param locations 库位，记录仓库中每个具体存储位置的详细信息
     * @return 结果
     */
    @Override
    public int insertLocations(Locations locations) {
        locations.setCreateTime(DateUtils.getNowDate());
        return locationsMapper.insertLocations(locations);
    }

    /**
     * 修改库位，记录仓库中每个具体存储位置的详细信息
     *
     * @param locations 库位，记录仓库中每个具体存储位置的详细信息
     * @return 结果
     */
    @Override
    public int updateLocations(Locations locations) {
        locations.setUpdateTime(DateUtils.getNowDate());
        return locationsMapper.updateLocations(locations);
    }

    /**
     * 批量删除库位，记录仓库中每个具体存储位置的详细信息
     *
     * @param locationIds 需要删除的库位，记录仓库中每个具体存储位置的详细信息主键
     * @return 结果
     */
    @Override
    public int deleteLocationsByLocationIds(Long[] locationIds) {
        return locationsMapper.deleteLocationsByLocationIds(locationIds);
    }

    /**
     * 删除库位，记录仓库中每个具体存储位置的详细信息信息
     *
     * @param locationId 库位，记录仓库中每个具体存储位置的详细信息主键
     * @return 结果
     */
    @Override
    public int deleteLocationsByLocationId(Long locationId) {
        return locationsMapper.deleteLocationsByLocationId(locationId);
    }
}
