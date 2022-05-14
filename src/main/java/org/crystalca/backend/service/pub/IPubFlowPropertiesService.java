package org.crystalca.backend.service.pub;

import org.crystalca.backend.entity.pub.PubFlowProperties;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.pub.CopyFromPublicDatabaseFilter;
import org.crystalca.backend.model.pub.PubFlowPropertiesGridFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-17
 */
public interface IPubFlowPropertiesService extends IService<PubFlowProperties> {
    GridData<PubFlowProperties> getGrid(PubFlowPropertiesGridFilter filter);

    String copy(CopyFromPublicDatabaseFilter data);
}
