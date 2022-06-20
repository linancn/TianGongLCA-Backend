package earth.tiangong.lca.backend.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import earth.tiangong.lca.backend.entity.SysRoles;
import earth.tiangong.lca.backend.security.model.LoginUser;
import earth.tiangong.lca.backend.security.util.SecurityUtils;
import earth.tiangong.lca.backend.security.util.StringUtils;
import earth.tiangong.lca.backend.service.ISysUserRolesService;

/**
 * 自定义权限实现，ss取自SpringSecurity首字母
 */
@Service("ss")
public class PermissionService
{
    /** 管理员角色权限标识 */
    private static final String SUPER_ADMIN = "admin";

    private static final String ROLE_DELIMETER = ",";

    @Autowired
    private ISysUserRolesService iSysUserRolesService;

    /**
     * 判断用户是否拥有某个角色
     * 
     * @param role 角色字符串
     * @return 用户是否具备某角色
     */
    public boolean hasRole(String role)
    {
        if (StringUtils.isEmpty(role))
        {
            return false;
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        List<SysRoles> sysRoles = iSysUserRolesService.getRolesByUserId(loginUser.getUserId());
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(sysRoles))
        {
            return false;
        }
        for (SysRoles sysRole : sysRoles)
        {
            String roleKey = sysRole.getName();
            if (SUPER_ADMIN.equals(roleKey) || roleKey.equals(StringUtils.trim(role)))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证用户是否不具备某角色，与 isRole逻辑相反。
     *
     * @param role 角色名称
     * @return 用户是否不具备某角色
     */
    public boolean lacksRole(String role)
    {
        return hasRole(role) != true;
    }

    /**
     * 验证用户是否具有以下任意一个角色
     *
     * @param roles 以 ROLE_NAMES_DELIMETER 为分隔符的角色列表
     * @return 用户是否具有以下任意一个角色
     */
    public boolean hasAnyRoles(String roles)
    {
        if (StringUtils.isEmpty(roles))
        {
            return false;
        }
        LoginUser loginUser = SecurityUtils.getLoginUser();
        List<SysRoles> sysRoles = iSysUserRolesService.getRolesByUserId(loginUser.getUserId());
        if (StringUtils.isNull(loginUser) || CollectionUtils.isEmpty(sysRoles))
        {
            return false;
        }
        for (String role : roles.split(ROLE_DELIMETER))
        {
            if (hasRole(role))
            {
                return true;
            }
        }
        return false;
    }
}
