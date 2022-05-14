package org.crystalca.backend.service;

import org.crystalca.backend.entity.UnitGroupsView;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.UnitGroupsViewGridFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-17
 */
public interface IUnitGroupsViewService extends IService<UnitGroupsView> {
    UnitGroupsView getByDataId(Long projectId, String id);

    UnitGroupsView getByPkid(Long pkid);

    GridData<UnitGroupsView> getGrid(UnitGroupsViewGridFilter filter);
}
