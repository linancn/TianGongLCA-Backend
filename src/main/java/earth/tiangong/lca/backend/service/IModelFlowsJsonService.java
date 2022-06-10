package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.ModelFlowsJson;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.ModelFlowsJsonExtension;
import earth.tiangong.lca.backend.model.ModelFlowsJsonGridFilter;

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
public interface IModelFlowsJsonService extends IService<ModelFlowsJson> {
    List<ModelFlowsJson> getByPlanPkid(Long planPkid);

    ModelFlowsJson getById(Long projectId, String planId, String edgeSourceId, String edgeTargetId, String planSourceId, String planTargetId, String processSourceId, String processTargetId);

    GridData<ModelFlowsJsonExtension> getGrid(ModelFlowsJsonGridFilter filter);
}
