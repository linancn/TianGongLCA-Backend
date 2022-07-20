package earth.tiangong.lca.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import earth.tiangong.lca.backend.entity.SysUsersView;
import earth.tiangong.lca.backend.mapper.SysUsersViewMapper;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.UserGridFilter;
import earth.tiangong.lca.backend.service.ISysUsersViewService;
import earth.tiangong.lca.backend.util.ColumnNameUtil;

@Service
public class SysUsersViewServiceImpl extends ServiceImpl<SysUsersViewMapper, SysUsersView> implements ISysUsersViewService {
    @Autowired
    SysUsersViewMapper sysUsersViewMapper;

    public SysUsersView getById(Long id) {
        return sysUsersViewMapper.selectById(id);
    }
    @Override
    public GridData<SysUsersView> getGrid(UserGridFilter filter) {
        QueryWrapper<SysUsersView> queryWrapper = new QueryWrapper<SysUsersView>();
        if (filter.getName() != null && !filter.getName().trim().equals(""))
            queryWrapper.like(ColumnNameUtil.toDatabaseName("name"), filter.getName());
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<SysUsersView> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<SysUsersView> resultPage = sysUsersViewMapper.selectPage(page, queryWrapper);
        GridData<SysUsersView> gridData = new GridData<SysUsersView>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }

}
