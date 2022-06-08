package org.tiangonglca.backend.service.impl;

import org.tiangonglca.backend.entity.ParametersJson;
import org.tiangonglca.backend.mapper.ParametersJsonMapper;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.ParametersJsonGridFilter;
import org.tiangonglca.backend.service.IParametersJsonService;
import org.tiangonglca.backend.util.ColumnNameUtil;

import java.util.List;

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
 * @author TianGongLCA
 * @since 2022-01-20
 */
@Service
public class ParametersJsonServiceImpl extends ServiceImpl<ParametersJsonMapper, ParametersJson> implements IParametersJsonService {
    @Autowired
    ParametersJsonMapper parametersJsonMapper;

    public GridData<ParametersJson> getGrid(ParametersJsonGridFilter filter) {
        QueryWrapper<ParametersJson> queryWrapper = new QueryWrapper<ParametersJson>();
        if (filter.getProjectId() != null && filter.getProcessId() != null) {
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("processId"), filter.getProcessId());
        } else {
            GridData<ParametersJson> gridData = new GridData<ParametersJson>();
            return gridData;
        }
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<ParametersJson> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<ParametersJson> parameterPage = parametersJsonMapper.selectPage(page, queryWrapper);
        GridData<ParametersJson> gridData = new GridData<ParametersJson>(parameterPage.getRecords(), parameterPage.getTotal(), parameterPage.getCurrent(), parameterPage.getSize(), true);
        return gridData;
    }

    @Transactional
    public String create(ParametersJson parameter) {
        try {
            return parametersJsonMapper.insert(parameter) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String update(ParametersJson parameter) {
        try {
            return parametersJsonMapper.updateById(parameter) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String delete(Long id) {
        try {
            return parametersJsonMapper.deleteById(id) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public ParametersJson getByDataId(Long projectId, String processId, String id) {
        QueryWrapper<ParametersJson> queryWrapper = new QueryWrapper<ParametersJson>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("processId"), processId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        ParametersJson parameterJson = parametersJsonMapper.selectOne(queryWrapper);
        return parameterJson;
    }

    public List<ParametersJson> getListByDataId(Long projectId, String processId) {
        QueryWrapper<ParametersJson> queryWrapper = new QueryWrapper<ParametersJson>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("processId"), processId);
        return parametersJsonMapper.selectList(queryWrapper);
    }
}
