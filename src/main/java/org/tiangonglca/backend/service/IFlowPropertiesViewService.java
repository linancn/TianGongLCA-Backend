package org.tiangonglca.backend.service;

import org.tiangonglca.backend.entity.FlowPropertiesView;
import org.tiangonglca.backend.model.FlowPropertiesViewGridFilter;
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
public interface IFlowPropertiesViewService extends IService<FlowPropertiesView> {
    FlowPropertiesView getByDataId(Long projectId, String id);
    FlowPropertiesView getByPkid(Long pkid);
    GridData<FlowPropertiesView> getGrid(FlowPropertiesViewGridFilter filter);
}
