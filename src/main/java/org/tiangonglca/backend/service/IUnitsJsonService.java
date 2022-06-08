package org.tiangonglca.backend.service;

import org.tiangonglca.backend.entity.UnitsJson;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.UnitsJsonGridFilter;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-17
 */
public interface IUnitsJsonService extends IService<UnitsJson> {
    UnitsJson getByDataId(Long projectId, String unitGroupId, String id);

    List<UnitsJson> getListByDataId(Long projectId, String unitGroupId);

    GridData<UnitsJson> getGrid(UnitsJsonGridFilter filter);
}
