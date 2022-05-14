package org.crystalca.backend.service;

import org.crystalca.backend.entity.UnitGroups;
import org.crystalca.backend.entity.UnitsJson;
import org.crystalca.backend.model.CopyFromOtherProjectsFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-17
 */
public interface IUnitGroupsService extends IService<UnitGroups> {

    UnitGroups getByDataId(Long projectId, String id);

    String insert(UnitGroups data);

    String create(UnitGroups data);

    String copy(CopyFromOtherProjectsFilter filter);

    String updateBase(UnitGroups data);

    String updateCategory(UnitGroups data);

    String updateUnitJson(UnitsJson data,String option);
}
