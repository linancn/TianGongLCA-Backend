package org.crystalca.backend.service.pub;

import org.crystalca.backend.entity.pub.PubFlows;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.pub.PubFlowsGridFilter;
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
public interface IPubFlowsService extends IService<PubFlows> {
    GridData<PubFlows> getGrid(PubFlowsGridFilter filter);

    String copy(CopyFromPublicDatabaseFilter data);
}
