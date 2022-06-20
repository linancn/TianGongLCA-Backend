package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.SysRoles;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-13
 */
public interface ISysRolesService extends IService<SysRoles> {
    SysRoles getById(Long id);
}
