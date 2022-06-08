package org.tiangonglca.backend.service.pub;

import org.tiangonglca.backend.entity.pub.PubProcesses;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.pub.PubProcessesGridFilter;
import org.tiangonglca.backend.model.pub.CopyFromPublicDatabaseFilter;

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
