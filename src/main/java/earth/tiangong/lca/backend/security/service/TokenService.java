package earth.tiangong.lca.backend.security.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import earth.tiangong.lca.backend.entity.SysUsers;
import earth.tiangong.lca.backend.security.model.LoginUser;
import earth.tiangong.lca.backend.security.util.Constants;
import earth.tiangong.lca.backend.security.util.IdUtils;
import earth.tiangong.lca.backend.security.util.StringUtils;
import earth.tiangong.lca.backend.service.ISysUsersService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * token验证处理
 */
@Component
public class TokenService {
    // 令牌自定义标识
    // @Value("${token.header}")
    private String header = "Authorization";

    // 令牌秘钥
    // @Value("${token.secret}")
    private String secret = "abcdefghijklmnopqrstuvwxyz";

    // 令牌有效期
    // @Value("${token.expireTime}")
    private int expireTime = 60;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 30 * 60 * 1000L;

    @Autowired
    private ISysUsersService iSysUsersService;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token)) {
            try {
                Claims claims = parseToken(token);
                // 解析对应的权限以及用户信息
                String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
                SysUsers sysUser = iSysUsersService.getByTokenKey(uuid);
                LoginUser loginUser = new LoginUser(sysUser);
                return loginUser;
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 删除用户身份信息
     */
    public void delLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            iSysUsersService.deleteTokenKey(token);
        }
    }

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(LoginUser loginUser) {
        String token = IdUtils.fastUUID();
        loginUser.setToken(token);
        iSysUsersService.updateTokenKey(loginUser.getUserId(), loginUser.getToken());
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * 验证令牌有效期，相差不足30分钟，自动刷新缓存
     *
     * @param loginUser
     * @return 令牌
     */
    public Boolean verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= 0)
            return false;
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
        return true;
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser) {
        Long currentTime = System.currentTimeMillis();
        Long tokenExpireTime = currentTime + expireTime * MILLIS_MINUTE;
        iSysUsersService.updateTokenExpireTime(loginUser.getToken(), new Timestamp(tokenExpireTime));
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims) {
        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }
}
