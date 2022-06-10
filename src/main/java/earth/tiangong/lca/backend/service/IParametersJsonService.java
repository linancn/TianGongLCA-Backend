package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.ParametersJson;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.ParametersJsonGridFilter;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-01-20
 */
public interface IParametersJsonService extends IService<ParametersJson> {
    GridData<ParametersJson> getGrid(ParametersJsonGridFilter filter);

    ParametersJson getByDataId(Long projectId, String processId, String id);

    List<ParametersJson> getListByDataId(Long projectId, String processId);
}
