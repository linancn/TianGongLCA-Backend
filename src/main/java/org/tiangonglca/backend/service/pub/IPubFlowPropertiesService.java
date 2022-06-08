package org.tiangonglca.backend.service.pub;

import org.tiangonglca.backend.entity.pub.PubFlowProperties;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.pub.CopyFromPublicDatabaseFilter;
import org.tiangonglca.backend.model.pub.PubFlowPropertiesGridFilter;

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
