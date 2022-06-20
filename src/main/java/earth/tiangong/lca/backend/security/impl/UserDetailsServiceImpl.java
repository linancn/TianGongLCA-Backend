package earth.tiangong.lca.backend.security.impl;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import earth.tiangong.lca.backend.entity.SysUsers;
import earth.tiangong.lca.backend.security.exception.ServiceException;
import earth.tiangong.lca.backend.security.model.LoginUser;
import earth.tiangong.lca.backend.security.util.StringUtils;
import earth.tiangong.lca.backend.service.ISysUsersService;

/**
 * 用户验证处理
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUsersService usersService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUsers user = usersService.getByName(username);
        if (StringUtils.isNull(user)) {
            throw new ServiceException("登录用户：" + username + " 不存在");
        } else if (user.getIsDeleted() != null && user.getIsDeleted()) {
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUsers user) {
        return new LoginUser(user);
    }
}
