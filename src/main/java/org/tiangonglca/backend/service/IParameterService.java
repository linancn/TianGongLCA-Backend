package org.tiangonglca.backend.service;

import org.tiangonglca.backend.entity.Parameter;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.ParameterGridFilter;

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
