package org.crystalca.backend.service;

import org.crystalca.backend.entity.ModelFlowsJsonView;
import org.crystalca.backend.entity.Plans;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.PlansGridFilter;
import org.crystalca.backend.model.PlansExtension;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CrystaLCA
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
