package org.tiangonglca.backend.service.impl;

import org.tiangonglca.backend.entity.UnitGroupsView;
import org.tiangonglca.backend.mapper.UnitGroupsViewMapper;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.UnitGroupsViewGridFilter;
import org.tiangonglca.backend.service.IUnitGroupsViewService;
import org.tiangonglca.backend.util.ColumnNameUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-17
 */
@Service
public class UnitGroupsViewServiceImpl extends ServiceImpl<UnitGroupsViewMapper, UnitGroupsView> implements IUnitGroupsViewService {
    @Autowired
    UnitGroupsViewMapper unitGroupsViewMapper;

    public UnitGroupsView getByDataId(Long projectId, String id) {
        QueryWrapper<UnitGroupsView> queryWrapper = new QueryWrapper<UnitGroupsView>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        return unitGroupsViewMapper.selectOne(queryWrapper);
    }

    public UnitGroupsView getByPkid(Long pkid) {
        return unitGroupsViewMapper.selectById(pkid);
    }

    public GridData<UnitGroupsView> getGrid(UnitGroupsViewGridFilter filter) {
        QueryWrapper<UnitGroupsView> queryWrapper = new QueryWrapper<UnitGroupsView>();
        if (filter.getProjectId() != null) {
            if (filter.getOtherProject())
                queryWrapper.ne(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
            else
                queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
        } else
            return new GridData<>();
        if (filter.getDataName() != null && !filter.getDataName().equals(""))
            queryWrapper.like(ColumnNameUtil.toDatabaseName("dataName"), filter.getDataName());
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<UnitGroupsView> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<UnitGroupsView> resultPage = unitGroupsViewMapper.selectPage(page, queryWrapper);
        GridData<UnitGroupsView> gridData = new GridData<UnitGroupsView>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }
}
