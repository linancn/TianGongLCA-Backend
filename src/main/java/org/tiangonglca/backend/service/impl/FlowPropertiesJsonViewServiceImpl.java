package org.tiangonglca.backend.service.impl;

import org.tiangonglca.backend.entity.FlowPropertiesJsonView;
import org.tiangonglca.backend.mapper.FlowPropertiesJsonViewMapper;
import org.tiangonglca.backend.model.FlowPropertiesJsonViewGridFilter;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.service.IFlowPropertiesJsonViewService;
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
 * @since 2022-02-13
 */
@Service
public class FlowPropertiesJsonViewServiceImpl extends ServiceImpl<FlowPropertiesJsonViewMapper, FlowPropertiesJsonView> implements IFlowPropertiesJsonViewService {
    @Autowired
    FlowPropertiesJsonViewMapper flowPropertiesJsonViewMapper;

    public FlowPropertiesJsonView getByDataId(Long projectId, String flowId, String propertyId) {
        QueryWrapper<FlowPropertiesJsonView> queryWrapper = new QueryWrapper<FlowPropertiesJsonView>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("flowId"), flowId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("flowPropertyId"), propertyId);
        return flowPropertiesJsonViewMapper.selectOne(queryWrapper);
    }

    public GridData<FlowPropertiesJsonView> getGrid(FlowPropertiesJsonViewGridFilter filter) {
        QueryWrapper<FlowPropertiesJsonView> queryWrapper = new QueryWrapper<FlowPropertiesJsonView>();
        if (filter.getProjectId() != null)
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
        else
            return new GridData<>();

        if (filter.getFlowId() != null)
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("flowId"), filter.getFlowId());
        else
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
        Page<FlowPropertiesJsonView> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<FlowPropertiesJsonView> resultPage = flowPropertiesJsonViewMapper.selectPage(page, queryWrapper);
        GridData<FlowPropertiesJsonView> gridData = new GridData<FlowPropertiesJsonView>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }
}
