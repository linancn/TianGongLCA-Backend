package org.crystalca.backend.service.impl;

import org.crystalca.backend.entity.ModelFlowsJson;
import org.crystalca.backend.entity.ModelNodesJson;
import org.crystalca.backend.entity.Processes;
import org.crystalca.backend.mapper.ModelFlowsJsonMapper;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.ModelFlowsJsonExtension;
import org.crystalca.backend.model.ModelFlowsJsonGridFilter;
import org.crystalca.backend.service.IModelFlowsJsonService;
import org.crystalca.backend.service.IModelNodesJsonService;
import org.crystalca.backend.service.IProcessesService;
import org.crystalca.backend.util.ColumnNameUtil;

import java.util.ArrayList;
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
public class ModelFlowsJsonServiceImpl extends ServiceImpl<ModelFlowsJsonMapper, ModelFlowsJson> implements IModelFlowsJsonService {
    @Autowired
    ModelFlowsJsonMapper modelFlowsJsonMapper;

    @Autowired
    IModelNodesJsonService iModelNodesJsonService;

    public List<ModelFlowsJson> getByPlanPkid(Long planPkid) {
        QueryWrapper<ModelFlowsJson> queryWrapper = new QueryWrapper<ModelFlowsJson>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("planPkid"), planPkid);
        return modelFlowsJsonMapper.selectList(queryWrapper);
    }

    public ModelFlowsJson getById(Long projectId, String planId, String edgeSourceId, String edgeTargetId, String planSourceId, String planTargetId, String processSourceId, String processTargetId) {
        QueryWrapper<ModelFlowsJson> queryWrapper = new QueryWrapper<ModelFlowsJson>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("planId"), planId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("edgeSourceId"), edgeSourceId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("edgeTargetId"), edgeTargetId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("planSourceId"), planSourceId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("planTargetId"), planTargetId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("processSourceId"), processSourceId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("processTargetId"), processTargetId);
        return modelFlowsJsonMapper.selectOne(queryWrapper);
    }

    @Autowired
    IProcessesService iProcessesService;

    public GridData<ModelFlowsJsonExtension> getGrid(ModelFlowsJsonGridFilter filter) {
        QueryWrapper<ModelFlowsJson> queryWrapper = new QueryWrapper<ModelFlowsJson>();

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

        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<ModelFlowsJson> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<ModelFlowsJson> resultPage = modelFlowsJsonMapper.selectPage(page, queryWrapper);

        List<ModelFlowsJsonExtension> records = new ArrayList<ModelFlowsJsonExtension>();
        for (ModelFlowsJson m : resultPage.getRecords()) {
            ModelFlowsJsonExtension me = new ModelFlowsJsonExtension(m);
            if (!(me.getEdgeSourceId().equals(me.getProcessSourceId()))) {
                String planId = me.getEdgeSourceId();
                if (me.getPlanSourceId() != null && (!me.getPlanSourceId().equals(""))) {
                    String[] planSourceIds = me.getPlanSourceId().split("_");
                    for (String id : planSourceIds) {
                        ModelNodesJson node = iModelNodesJsonService.getByDataId(filter.getProjectId(), planId, id, "plan");
                        if (node != null) {
                            if (me.getPlanSourceName().equals(""))
                                me.setPlanSourceName(node.getNodeName());
                            else
                                me.setPlanSourceName(me.getPlanSourceName() + "_" + node.getNodeName());
                            planId = id;
                        } else
                            me.setAvailable(false);
                        break;
                    }
                }
                if (me.getAvailable()) {
                    ModelNodesJson node = iModelNodesJsonService.getByDataId(filter.getProjectId(), planId, me.getProcessSourceId(), "process");
                    if (node != null) {
                        me.setProcessSourceName(node.getNodeName());
                    } else
                        me.setAvailable(false);
                }
            } else {
                Processes process = iProcessesService.getByDataId(filter.getProjectId(), me.getProcessSourceId());
                if (process != null) {
                    me.setProcessSourceName(process.getDataName());
                } else
                    me.setAvailable(false);
            }

            if (!(me.getEdgeTargetId().equals(me.getProcessTargetId()))) {
                String planId = me.getEdgeTargetId();
                if (me.getPlanTargetId() != null && (!me.getPlanTargetId().equals(""))) {
                    String[] planTargetIds = me.getPlanTargetId().split("_");
                    for (String id : planTargetIds) {
                        ModelNodesJson node = iModelNodesJsonService.getByDataId(filter.getProjectId(), planId, id, "plan");
                        if (node != null) {
                            if (me.getPlanTargetName().equals(""))
                                me.setPlanTargetName(node.getNodeName());
                            else
                                me.setPlanTargetName(me.getPlanTargetName() + "_" + node.getNodeName());
                            planId = id;
                        } else
                            me.setAvailable(false);
                        break;
                    }
                }
                if (me.getAvailable()) {
                    ModelNodesJson node = iModelNodesJsonService.getByDataId(filter.getProjectId(), planId, me.getProcessTargetId(), "process");
                    if (node != null) {
                        me.setProcessTargetName(node.getNodeName());
                    } else
                        me.setAvailable(false);
                }
            } else {
                Processes process = iProcessesService.getByDataId(filter.getProjectId(), me.getProcessTargetId());
                if (process != null) {
                    me.setProcessTargetName(process.getDataName());
                } else
                    me.setAvailable(false);
            }
            
            records.add(me);
        }

        GridData<ModelFlowsJsonExtension> gridData = new GridData<ModelFlowsJsonExtension>(records, resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }
}
