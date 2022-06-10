package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.ExchangesJson;
import earth.tiangong.lca.backend.entity.ParametersJson;
import earth.tiangong.lca.backend.entity.Processes;
import earth.tiangong.lca.backend.model.CopyFromOtherProjectsFilter;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.ProcessesGridFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
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
