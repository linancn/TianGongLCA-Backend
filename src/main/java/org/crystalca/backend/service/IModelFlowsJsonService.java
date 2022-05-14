package org.crystalca.backend.service;

import org.crystalca.backend.entity.ModelFlowsJson;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.ModelFlowsJsonExtension;
import org.crystalca.backend.model.ModelFlowsJsonGridFilter;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-01-20
 */
public interface IModelFlowsJsonService extends IService<ModelFlowsJson> {
    List<ModelFlowsJson> getByPlanPkid(Long planPkid);

    ModelFlowsJson getById(Long projectId, String planId, String edgeSourceId, String edgeTargetId, String planSourceId, String planTargetId, String processSourceId, String processTargetId);

    GridData<ModelFlowsJsonExtension> getGrid(ModelFlowsJsonGridFilter filter);
}
