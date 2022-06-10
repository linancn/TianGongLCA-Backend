package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.UnitGroupsView;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.UnitGroupsViewGridFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-17
 */
public interface IUnitGroupsViewService extends IService<UnitGroupsView> {
    UnitGroupsView getByDataId(Long projectId, String id);

    UnitGroupsView getByPkid(Long pkid);

    GridData<UnitGroupsView> getGrid(UnitGroupsViewGridFilter filter);
}
