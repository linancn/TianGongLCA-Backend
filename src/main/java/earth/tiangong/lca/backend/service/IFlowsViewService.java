package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.FlowsView;
import earth.tiangong.lca.backend.model.FlowsViewGridFilter;
import earth.tiangong.lca.backend.model.GridData;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-13
 */
public interface IFlowsViewService extends IService<FlowsView> {
    FlowsView getByPkid(Long pkid);

    FlowsView getByDataId(Long projectId, String id);

    GridData<FlowsView> getGrid(FlowsViewGridFilter filter);
}
