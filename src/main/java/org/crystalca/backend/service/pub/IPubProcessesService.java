package org.crystalca.backend.service.pub;

import org.crystalca.backend.entity.pub.PubProcesses;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.pub.PubProcessesGridFilter;
import org.crystalca.backend.model.pub.CopyFromPublicDatabaseFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-13
 */
public interface IPubProcessesService extends IService<PubProcesses> {
    GridData<PubProcesses> getGrid(PubProcessesGridFilter filter);

    String copy(CopyFromPublicDatabaseFilter data);
}
