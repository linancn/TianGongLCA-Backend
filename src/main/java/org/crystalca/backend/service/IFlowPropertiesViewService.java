package org.crystalca.backend.service;

import org.crystalca.backend.entity.FlowPropertiesView;
import org.crystalca.backend.model.FlowPropertiesViewGridFilter;
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
public interface IFlowPropertiesViewService extends IService<FlowPropertiesView> {
    FlowPropertiesView getByDataId(Long projectId, String id);
    FlowPropertiesView getByPkid(Long pkid);
    GridData<FlowPropertiesView> getGrid(FlowPropertiesViewGridFilter filter);
}
