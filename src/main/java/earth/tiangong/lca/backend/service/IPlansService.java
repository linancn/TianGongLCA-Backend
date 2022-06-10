package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.ModelFlowsJsonView;
import earth.tiangong.lca.backend.entity.Plans;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.PlansGridFilter;
import earth.tiangong.lca.backend.model.PlansExtension;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-01-17
 */
public interface IPlansService extends IService<Plans> {
    GridData<Plans> getGrid(PlansGridFilter filter);

    String create(Plans data);

    String updateBase(Plans data);

    String delete(Long id);

    Plans getBaseByPkid(Long pkid);

    Plans getBaseByDataId(Long projectId, String id);

    PlansExtension getModelCells(Long projectId, String id);

    String updateModelCells(long projecId, String id, String modelCells);

    String updateModelFlows(ModelFlowsJsonView data, String option);

    GridData<Plans> getParentGrid(PlansGridFilter filter);
}
