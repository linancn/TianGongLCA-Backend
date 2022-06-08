package org.tiangonglca.backend.service.pub;

import org.tiangonglca.backend.entity.pub.PubFlows;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.pub.PubFlowsGridFilter;
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
public interface IPubFlowsService extends IService<PubFlows> {
    GridData<PubFlows> getGrid(PubFlowsGridFilter filter);

    String copy(CopyFromPublicDatabaseFilter data);
}
