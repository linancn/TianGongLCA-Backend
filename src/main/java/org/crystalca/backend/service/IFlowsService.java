package org.crystalca.backend.service;

import org.crystalca.backend.entity.Flows;
import org.crystalca.backend.model.CopyFromOtherProjectsFilter;
import org.crystalca.backend.entity.FlowPropertiesJson;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CrystaLCA
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
