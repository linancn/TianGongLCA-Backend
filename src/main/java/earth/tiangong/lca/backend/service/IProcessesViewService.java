package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.ProcessesView;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-03-13
 */
public interface IProcessesViewService extends IService<ProcessesView> {

    ProcessesView getByDataId(Long projectId, String id);

    ProcessesView getByPkid(Long pkid);
}
