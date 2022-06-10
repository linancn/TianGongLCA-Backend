package earth.tiangong.lca.backend.service.pub;

import earth.tiangong.lca.backend.entity.pub.PubFlowProperties;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.pub.CopyFromPublicDatabaseFilter;
import earth.tiangong.lca.backend.model.pub.PubFlowPropertiesGridFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-17
 */
public interface IPubFlowPropertiesService extends IService<PubFlowProperties> {
    GridData<PubFlowProperties> getGrid(PubFlowPropertiesGridFilter filter);

    String copy(CopyFromPublicDatabaseFilter data);
}
