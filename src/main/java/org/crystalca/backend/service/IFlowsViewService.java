package org.crystalca.backend.service;

import org.crystalca.backend.entity.FlowsView;
import org.crystalca.backend.model.FlowsViewGridFilter;
import org.crystalca.backend.model.GridData;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-13
 */
public interface IFlowsViewService extends IService<FlowsView> {
    FlowsView getByPkid(Long pkid);

    FlowsView getByDataId(Long projectId, String id);

    GridData<FlowsView> getGrid(FlowsViewGridFilter filter);
}
