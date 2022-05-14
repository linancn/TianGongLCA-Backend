package org.crystalca.backend.service;

import org.crystalca.backend.entity.ParametersJson;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.ParametersJsonGridFilter;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-01-20
 */
public interface IParametersJsonService extends IService<ParametersJson> {
    GridData<ParametersJson> getGrid(ParametersJsonGridFilter filter);

    ParametersJson getByDataId(Long projectId, String processId, String id);

    List<ParametersJson> getListByDataId(Long projectId, String processId);
}
