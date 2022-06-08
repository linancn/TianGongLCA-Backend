package org.tiangonglca.backend.service;

import org.tiangonglca.backend.entity.UnitGroupsView;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.UnitGroupsViewGridFilter;

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
