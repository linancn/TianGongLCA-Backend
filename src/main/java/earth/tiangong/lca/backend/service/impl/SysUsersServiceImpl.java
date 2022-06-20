package earth.tiangong.lca.backend.service.impl;

import earth.tiangong.lca.backend.entity.SysUsers;
import earth.tiangong.lca.backend.mapper.SysUsersMapper;
import earth.tiangong.lca.backend.service.ISysUsersService;
import earth.tiangong.lca.backend.util.ColumnNameUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.sql.Timestamp;
import java.util.Date;

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
public class SysUsersServiceImpl extends ServiceImpl<SysUsersMapper, SysUsers> implements ISysUsersService {
    @Autowired
    SysUsersMapper sysUsersMapper;

    public SysUsers getById(Long id) {
        return sysUsersMapper.selectById(id);
    }

    public SysUsers getByTokenKey(String tokenKey) {
        QueryWrapper<SysUsers> queryWrapper = new QueryWrapper<SysUsers>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("tokenKey"), tokenKey);
        return sysUsersMapper.selectOne(queryWrapper);
    }

    public SysUsers getByName(String name) {
        QueryWrapper<SysUsers> queryWrapper = new QueryWrapper<SysUsers>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("name"), name);
        return sysUsersMapper.selectOne(queryWrapper);
    }

    // public void updateTokenById(Long id, String tokenKey, Timestamp
    // tokenExpireTime) {
    // SysUsers sysUser = getById(id);
    // sysUser.setTokenKey(tokenKey);
    // sysUser.setTokenExpireTime(tokenExpireTime);
    // sysUsersMapper.updateById(sysUser);
    // }

    public void updateLoginTimeById(Long id) {
        SysUsers sysUser = getById(id);
        Date date = new Date();
        sysUser.setLoginTime(new Timestamp(date.getTime()));
        sysUsersMapper.updateById(sysUser);
    }

    public void updateTokenExpireTime(String tokenKey, Timestamp tokenExpireTime) {
        SysUsers sysUser = getByTokenKey(tokenKey);
        sysUser.setTokenExpireTime(tokenExpireTime);
        sysUsersMapper.updateById(sysUser);
    }

    public void updateTokenKey(Long id, String tokenKey){
        SysUsers sysUser = getById(id);
        sysUser.setTokenKey(tokenKey);
        sysUsersMapper.updateById(sysUser);
    }

    public void deleteTokenKey(String tokenKey) {
        SysUsers sysUser = getByTokenKey(tokenKey);
        sysUser.setTokenKey("");
        sysUser.setTokenExpireTime(new Timestamp(0));
        sysUsersMapper.updateById(sysUser);
    }
}