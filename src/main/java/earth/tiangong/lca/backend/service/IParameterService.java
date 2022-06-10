package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.Parameter;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.ParameterGridFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-01-20
 */
public interface IParameterService extends IService<Parameter> {
    GridData<Parameter> getGrid(ParameterGridFilter filter);

    String create(Parameter parameter);

    String update(Parameter parameter);

    String delete(Long id);

    Parameter getByDataId(Long projectId, String processId, String id);
}
