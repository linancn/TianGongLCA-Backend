package org.tiangonglca.backend.service;

import org.tiangonglca.backend.entity.FlowProperties;
import org.tiangonglca.backend.model.CopyFromOtherProjectsFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-17
 */
public interface IFlowPropertiesService extends IService<FlowProperties> {
    String insert(FlowProperties data);

    String create(FlowProperties data);

    String updateBase(FlowProperties data);

    String copy(CopyFromOtherProjectsFilter filter);

    String updateCategory(FlowProperties data);

    String updateUnitGroup(FlowProperties data);
}
