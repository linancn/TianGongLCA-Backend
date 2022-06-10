package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.ModelFlowsJsonView;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.ModelFlowsJsonViewGridFilter;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-01-20
 */
public interface IModelFlowsJsonViewService extends IService<ModelFlowsJsonView> {
    List<ModelFlowsJsonView> getList(Long projectId, String planId, String edgeSourceId, String edgeTargetId, String planSourceId, String planTargetId, String processSourceId, String processTargetId);

    ModelFlowsJsonView getByFlowId(Long planPkid, String edgeSourceId, String edgeTargetId, String flowSourceId, String flowTargetId);

    GridData<ModelFlowsJsonView> getGrid(ModelFlowsJsonViewGridFilter filter);
}
