package earth.tiangong.lca.backend.service.pub;

import earth.tiangong.lca.backend.entity.pub.PubProcesses;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.pub.PubProcessesGridFilter;
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
public interface IPubProcessesService extends IService<PubProcesses> {
    GridData<PubProcesses> getGrid(PubProcessesGridFilter filter);

    String copy(CopyFromPublicDatabaseFilter data);
}
