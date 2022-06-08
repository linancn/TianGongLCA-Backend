package org.tiangonglca.backend.service;

import org.tiangonglca.backend.entity.FlowPropertiesJsonView;
import org.tiangonglca.backend.model.FlowPropertiesJsonViewGridFilter;
import org.tiangonglca.backend.model.GridData;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-17
 */
public interface IFlowPropertiesJsonViewService extends IService<FlowPropertiesJsonView> {
    FlowPropertiesJsonView getByDataId(Long projectId, String flowId, String propertyId);
    GridData<FlowPropertiesJsonView> getGrid(FlowPropertiesJsonViewGridFilter filter);
}
