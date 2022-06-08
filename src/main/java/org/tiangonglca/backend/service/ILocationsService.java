package org.tiangonglca.backend.service;

import org.tiangonglca.backend.entity.Locations;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.LocationsGridFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-17
 */
public interface ILocationsService extends IService<Locations> {
    String create(Locations data);
    String update(Locations data);
    Locations getByDataId(Long projectId, String id);
    Locations getByPkid(Long pkid);
    GridData<Locations> getGrid(LocationsGridFilter filter);
}
