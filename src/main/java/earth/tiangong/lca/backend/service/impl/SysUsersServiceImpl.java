package earth.tiangong.lca.backend.service.impl;

import earth.tiangong.lca.backend.entity.SysUsers;
import earth.tiangong.lca.backend.mapper.SysUsersMapper;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.UserGridFilter;
import earth.tiangong.lca.backend.service.ISysUsersService;
import earth.tiangong.lca.backend.util.ColumnNameUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void updateTokenKey(Long id, String tokenKey) {
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

    @Override
    public GridData<SysUsers> getGrid(UserGridFilter filter) {
        QueryWrapper<SysUsers> queryWrapper = new QueryWrapper<SysUsers>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("isDeleted"), false);
        if (filter.getName() != null && !filter.getName().trim().equals(""))
            queryWrapper.like(ColumnNameUtil.toDatabaseName("name"), filter.getName());
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<SysUsers> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<SysUsers> resultPage = sysUsersMapper.selectPage(page, queryWrapper);
        GridData<SysUsers> gridData = new GridData<SysUsers>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }

    @Transactional
    public String create(SysUsers data) {
        try {
            Date date = new Date();
            data.setCreateTime(new Timestamp(date.getTime()));
            data.setIsDeleted(false);
            return sysUsersMapper.insert(data) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String update(SysUsers data) {
        try {
            return sysUsersMapper.updateById(data) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String delete(Long id) {
        try {
            return sysUsersMapper.deleteById(id) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}