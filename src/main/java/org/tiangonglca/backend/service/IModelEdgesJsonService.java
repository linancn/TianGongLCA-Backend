package org.tiangonglca.backend.service;

import org.tiangonglca.backend.entity.ModelEdgesJson;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-01-20
 */
public interface IModelEdgesJsonService extends IService<ModelEdgesJson> {
    List<ModelEdgesJson> getByPlanPkid(Long planPkid);
}
