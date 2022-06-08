package org.tiangonglca.backend.service.impl;

import org.tiangonglca.backend.entity.UnitsJson;
import org.tiangonglca.backend.mapper.UnitsJsonMapper;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.UnitsJsonGridFilter;
import org.tiangonglca.backend.service.IUnitsJsonService;
import org.tiangonglca.backend.util.ColumnNameUtil;

import java.util.List;

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
public class UnitsJsonServiceImpl extends ServiceImpl<UnitsJsonMapper, UnitsJson> implements IUnitsJsonService {
    @Autowired
    UnitsJsonMapper unitsJsonMapper;

    public UnitsJson getByDataId(Long projectId, String unitGroupId, String id) {
        QueryWrapper<UnitsJson> queryWrapper = new QueryWrapper<UnitsJson>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("unitGroupId"), unitGroupId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        return unitsJsonMapper.selectOne(queryWrapper);
    }

    public List<UnitsJson> getListByDataId(Long projectId, String unitGroupId) {
        QueryWrapper<UnitsJson> queryWrapper = new QueryWrapper<UnitsJson>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("unitGroupId"), unitGroupId);
        return unitsJsonMapper.selectList(queryWrapper);
    }

    public GridData<UnitsJson> getGrid(UnitsJsonGridFilter filter) {
        QueryWrapper<UnitsJson> queryWrapper = new QueryWrapper<UnitsJson>();
        if (filter.getProjectId() != null)
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
        else
            return new GridData<>();
        if (filter.getUnitGroupId() != null)
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("unitGroupId"), filter.getUnitGroupId());
        else
            return new GridData<>();
            
        if (filter.getName() != null && !filter.getName().equals(""))
            queryWrapper.like(ColumnNameUtil.toDatabaseName("name"), filter.getName());
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<UnitsJson> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<UnitsJson> resultPage = unitsJsonMapper.selectPage(page, queryWrapper);
        GridData<UnitsJson> gridData = new GridData<UnitsJson>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }
}
