package org.crystalca.backend.service;

import org.crystalca.backend.entity.Locations;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.LocationsGridFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-17
 */
public interface ILocationsService extends IService<Locations> {
    String create(Locations data);
    String update(Locations data);
    Locations getByDataId(Long projectId, String id);
    Locations getByPkid(Long pkid);
    GridData<Locations> getGrid(LocationsGridFilter filter);
}
