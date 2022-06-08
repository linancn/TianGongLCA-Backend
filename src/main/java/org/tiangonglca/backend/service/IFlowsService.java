package org.tiangonglca.backend.service;

import org.tiangonglca.backend.entity.Flows;
import org.tiangonglca.backend.model.CopyFromOtherProjectsFilter;
import org.tiangonglca.backend.entity.FlowPropertiesJson;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-13
 */
public interface IFlowsService extends IService<Flows> {
    String insert(Flows data);

    String create(Flows data);

    String copy(CopyFromOtherProjectsFilter filter);

    String updateBase(Flows data);

    String updateLocation(Flows data);

    String updateCategory(Flows data);

    String updatePropertyJson(String propertyId, FlowPropertiesJson data, String option);

    Flows getByPkid(Long pkid);
}
