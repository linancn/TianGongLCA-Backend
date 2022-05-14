package org.crystalca.backend.service;

import org.crystalca.backend.entity.ExchangesJson;
import org.crystalca.backend.entity.ParametersJson;
import org.crystalca.backend.entity.Processes;
import org.crystalca.backend.model.CopyFromOtherProjectsFilter;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.ProcessesGridFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-03-13
 */
public interface IProcessesService extends IService<Processes> {

    String insert(Processes data);

    String create(Processes data);

    String copy(CopyFromOtherProjectsFilter filter);

    String updateBase(Processes data);

    String updateParametersJson(ParametersJson data, String option);

    String updateExchangesJson(ExchangesJson data, String option);

    GridData<Processes> getGrid(ProcessesGridFilter filter);

    Processes getByDataId(Long projectId, String id);

    Processes getByPkid(Long pkid);
}
