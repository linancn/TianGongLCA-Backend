package earth.tiangong.lca.backend.security.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import earth.tiangong.lca.backend.security.exception.ServiceException;
import earth.tiangong.lca.backend.security.exception.UserPasswordNotMatchException;
import earth.tiangong.lca.backend.security.model.LoginUser;
import earth.tiangong.lca.backend.service.ISysUsersService;

/**
 * 登录校验方法
 */
@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysUsersService iSysUsersService;

    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 登录验证
     * 
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */
    public String login(String username, String password) {
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new UserPasswordNotMatchException();
            } else {
                throw new ServiceException(e.getMessage());
            }
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        iSysUsersService.updateLoginTimeById(userId);
    }
}
