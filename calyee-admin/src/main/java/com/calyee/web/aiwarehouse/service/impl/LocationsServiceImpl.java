package com.calyee.web.aiwarehouse.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.calyee.common.utils.DateUtils;
import com.calyee.web.aiwarehouse.assembler.ProductAssembler;
import com.calyee.web.aiwarehouse.domain.entity.Inventory;
import com.calyee.web.aiwarehouse.domain.entity.Locations;
import com.calyee.web.aiwarehouse.domain.entity.Products;
import com.calyee.web.aiwarehouse.domain.vo.ProductsVO;
import com.calyee.web.aiwarehouse.mapper.LocationsMapper;
import com.calyee.web.aiwarehouse.service.IInventoryService;
import com.calyee.web.aiwarehouse.service.ILocationsService;
import com.calyee.web.aiwarehouse.service.IProductsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 库位，记录仓库中每个具体存储位置的详细信息Service业务层处理
 *
 * @author CALYEE
 * @date 2024-11-09
 */
@Service
@AllArgsConstructor
public class LocationsServiceImpl extends ServiceImpl<LocationsMapper, Locations> implements ILocationsService {

    private LocationsMapper locationsMapper;
    private IProductsService productsService;
    private IInventoryService inventoryService;

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
    public int deleteLocationsByLocationIds(List<String> locationIds) {
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

    @Override
    public ProductsVO product(String locationIds) {
        Inventory inventory = inventoryService.selectOneByLocationsId(locationIds);
        if (Objects.isNull(inventory)) {
            return null;
        }
        Products products = productsService.selectProductsByProductId(inventory.getProductId());
        if (Objects.isNull(products)) {
            return null;
        }
        return ProductAssembler.toVO(products); // todo前端适配 写页面
    }
}
