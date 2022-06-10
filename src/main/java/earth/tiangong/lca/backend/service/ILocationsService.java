package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.Locations;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.LocationsGridFilter;

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
