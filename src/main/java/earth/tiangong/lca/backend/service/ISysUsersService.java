package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.SysUsers;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-13
 */
public interface ISysUsersService extends IService<SysUsers> {

    SysUsers getById(Long id);

    SysUsers getByTokenKey(String tokenKey);

    SysUsers getByName(String name);

    void updateLoginTimeById(Long id);

    void updateTokenExpireTime(String tokenKey, Timestamp tokenExpireTime);

    void deleteTokenKey(String tokenKey);

    void updateTokenKey(Long id, String tokenKey);
}
