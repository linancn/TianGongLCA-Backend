package org.crystalca.backend.service.impl;

import org.crystalca.backend.entity.Parameter;
import org.crystalca.backend.mapper.ParameterMapper;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.ParameterGridFilter;
import org.crystalca.backend.service.IParameterService;
import org.crystalca.backend.util.ColumnNameUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-01-20
 */
@Service
public class ParameterServiceImpl extends ServiceImpl<ParameterMapper, Parameter> implements IParameterService {
    @Autowired
    ParameterMapper parameterMapper;

    public GridData<Parameter> getGrid(ParameterGridFilter filter) {
        QueryWrapper<Parameter> queryWrapper = new QueryWrapper<Parameter>();
        if (filter.getProjectId() != null)
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
        if (filter.getProcessId() != null)
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("processId"), filter.getProcessId());
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<Parameter> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<Parameter> parameterPage = parameterMapper.selectPage(page, queryWrapper);
        GridData<Parameter> gridData = new GridData<Parameter>(parameterPage.getRecords(), parameterPage.getTotal(), parameterPage.getCurrent(), parameterPage.getSize(), true);
        return gridData;
    }

    @Transactional
    public String create(Parameter parameter) {
        try {
            return parameterMapper.insert(parameter) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String update(Parameter parameter) {
        try {
            return parameterMapper.updateById(parameter) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String delete(Long id) {
        try {
            return parameterMapper.deleteById(id) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Parameter getByDataId(Long projectId, String processId, String id) {
        QueryWrapper<Parameter> queryWrapper = new QueryWrapper<Parameter>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("processId"), processId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        Parameter parameter = parameterMapper.selectOne(queryWrapper);
        return parameter;
    }
}
