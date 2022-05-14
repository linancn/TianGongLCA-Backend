package org.crystalca.backend.service.impl;

import org.crystalca.backend.entity.ModelFlowsJsonView;
import org.crystalca.backend.mapper.ModelFlowsJsonViewMapper;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.ModelFlowsJsonViewGridFilter;
import org.crystalca.backend.service.IModelFlowsJsonViewService;
import org.crystalca.backend.util.ColumnNameUtil;

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
 * @author CrystaLCA
 * @since 2022-01-20
 */
@Service
public class ModelFlowsJsonViewServiceImpl extends ServiceImpl<ModelFlowsJsonViewMapper, ModelFlowsJsonView> implements IModelFlowsJsonViewService {
    @Autowired
    ModelFlowsJsonViewMapper modelFlowsJsonViewMapper;

    public GridData<ModelFlowsJsonView> getGrid(ModelFlowsJsonViewGridFilter filter) {
        QueryWrapper<ModelFlowsJsonView> queryWrapper = new QueryWrapper<ModelFlowsJsonView>();

        if (filter.getProjectId() != null)
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
        else
            return new GridData<>();

        if (filter.getPlanId() != null && !filter.getPlanId().trim().equals(""))
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("planId"), filter.getPlanId());
        else
            return new GridData<>();

        if (filter.getEdgeSourceId() != null && !filter.getEdgeSourceId().trim().equals(""))
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("edgeSourceId"), filter.getEdgeSourceId());
        else
            return new GridData<>();

        if (filter.getEdgeTargetId() != null && !filter.getEdgeTargetId().trim().equals(""))
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("edgeTargetId"), filter.getEdgeTargetId());
        else
            return new GridData<>();

        queryWrapper.eq(ColumnNameUtil.toDatabaseName("planSourceId"), filter.getPlanSourceId());
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("planTargetId"), filter.getPlanTargetId());

        if (filter.getProcessSourceId() != null && !filter.getProcessSourceId().trim().equals(""))
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("processSourceId"), filter.getProcessSourceId());
        else
            return new GridData<>();

        if (filter.getProcessTargetId() != null && !filter.getProcessTargetId().trim().equals(""))
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("processTargetId"), filter.getProcessTargetId());
        else
            return new GridData<>();

        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<ModelFlowsJsonView> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<ModelFlowsJsonView> resultPage = modelFlowsJsonViewMapper.selectPage(page, queryWrapper);
        GridData<ModelFlowsJsonView> gridData = new GridData<ModelFlowsJsonView>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }

    public List<ModelFlowsJsonView> getList(Long projectId, String planId, String edgeSourceId, String edgeTargetId, String planSourceId, String planTargetId, String processSourceId, String processTargetId) {
        QueryWrapper<ModelFlowsJsonView> queryWrapper = new QueryWrapper<ModelFlowsJsonView>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("planId"), planId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("edgeSourceId"), edgeSourceId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("edgeTargetId"), edgeTargetId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("planSourceId"), planSourceId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("planTargetId"), planTargetId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("processSourceId"), processSourceId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("processTargetId"), processTargetId);
        return modelFlowsJsonViewMapper.selectList(queryWrapper);
    }

    public ModelFlowsJsonView getByFlowId(Long planPkid, String edgeSourceId, String edgeTargetId, String flowSourceId, String flowTargetId) {
        QueryWrapper<ModelFlowsJsonView> queryWrapper = new QueryWrapper<ModelFlowsJsonView>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("planPkid"), planPkid);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("edgeSourceId"), edgeSourceId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("edgeTargetId"), edgeTargetId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("flowSourceId"), flowSourceId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("flowTargetId"), flowTargetId);
        return modelFlowsJsonViewMapper.selectOne(queryWrapper);
    }
}
