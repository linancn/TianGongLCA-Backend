package earth.tiangong.lca.backend.service;
import com.baomidou.mybatisplus.extension.service.IService;

import earth.tiangong.lca.backend.entity.SysUsersView;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.UserGridFilter;
public interface ISysUsersViewService  extends IService<SysUsersView> {
    SysUsersView getById(Long id);
    GridData<SysUsersView> getGrid(UserGridFilter filter);
}
