package earth.tiangong.lca.backend.service.pub;

import earth.tiangong.lca.backend.entity.pub.PubFlows;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.pub.PubFlowsGridFilter;
import earth.tiangong.lca.backend.model.pub.CopyFromPublicDatabaseFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-13
 */
public interface IPubFlowsService extends IService<PubFlows> {
    GridData<PubFlows> getGrid(PubFlowsGridFilter filter);

    String copy(CopyFromPublicDatabaseFilter data);
}
