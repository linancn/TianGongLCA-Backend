package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.UnitGroups;
import earth.tiangong.lca.backend.entity.UnitsJson;
import earth.tiangong.lca.backend.model.CopyFromOtherProjectsFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
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
