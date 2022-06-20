package earth.tiangong.lca.backend.service.impl;

import earth.tiangong.lca.backend.entity.SysRoles;
import earth.tiangong.lca.backend.mapper.SysRolesMapper;
import earth.tiangong.lca.backend.service.ISysRolesService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-13
 */
@Service
public class SysRolesServiceImpl extends ServiceImpl<SysRolesMapper, SysRoles> implements ISysRolesService {
    @Autowired
    SysRolesMapper sysRolesMapper;

    public SysRoles getById(Long id) {
        return sysRolesMapper.selectById(id);
    }

    // public Roles getByName(String name) {
    // QueryWrapper<Roles> queryWrapper = new QueryWrapper<Roles>();
    // queryWrapper.eq(ColumnNameUtil.toDatabaseName("name"), name);
    // return rolesMapper.selectOne(queryWrapper);
    // }
}