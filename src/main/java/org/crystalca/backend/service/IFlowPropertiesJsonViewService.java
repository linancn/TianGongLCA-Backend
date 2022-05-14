package org.crystalca.backend.service;

import org.crystalca.backend.entity.FlowPropertiesJsonView;
import org.crystalca.backend.model.FlowPropertiesJsonViewGridFilter;
import org.crystalca.backend.model.GridData;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-17
 */
public interface IFlowPropertiesJsonViewService extends IService<FlowPropertiesJsonView> {
    FlowPropertiesJsonView getByDataId(Long projectId, String flowId, String propertyId);
    GridData<FlowPropertiesJsonView> getGrid(FlowPropertiesJsonViewGridFilter filter);
}
