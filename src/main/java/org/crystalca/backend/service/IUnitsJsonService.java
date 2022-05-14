package org.crystalca.backend.service;

import org.crystalca.backend.entity.UnitsJson;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.UnitsJsonGridFilter;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-17
 */
public interface IUnitsJsonService extends IService<UnitsJson> {
    UnitsJson getByDataId(Long projectId, String unitGroupId, String id);

    List<UnitsJson> getListByDataId(Long projectId, String unitGroupId);

    GridData<UnitsJson> getGrid(UnitsJsonGridFilter filter);
}
