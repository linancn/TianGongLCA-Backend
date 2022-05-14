package org.crystalca.backend.service.impl;

import org.crystalca.backend.entity.ModelFlowsJson;
import org.crystalca.backend.entity.ModelFlowsJsonView;
import org.crystalca.backend.entity.Plans;
import org.crystalca.backend.mapper.PlansMapper;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.PlansExtension;
import org.crystalca.backend.model.PlansGridFilter;
import org.crystalca.backend.service.IModelFlowsJsonService;
import org.crystalca.backend.service.IModelFlowsJsonViewService;
import org.crystalca.backend.service.IPlansService;
import org.crystalca.backend.util.ColumnNameUtil;
import org.crystalca.backend.util.DataVersionUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlansServiceImpl extends ServiceImpl<PlansMapper, Plans> implements IPlansService {
    @Autowired
    PlansMapper plansMapper;

    public String[] baseColumns = { "pkid", "project_id", "id", "data_name", "plan_type", "description", "version", "last_change" };

    public GridData<Plans> getGrid(PlansGridFilter filter) {
        QueryWrapper<Plans> queryWrapper = new QueryWrapper<Plans>();
        if (filter.getProjectId() != null)
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
        else
            return new GridData<>();
        if (filter.getDataName() != null && !filter.getDataName().trim().equals(""))
            queryWrapper.like(ColumnNameUtil.toDatabaseName("dataName"), filter.getDataName());
        if (filter.getPlanType() != null && !filter.getPlanType().trim().equals(""))
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("planType"), filter.getPlanType());
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<Plans> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<Plans> resultPage = plansMapper.selectPage(page, queryWrapper);
        GridData<Plans> gridData = new GridData<Plans>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }

    @Transactional
    public String create(Plans data) {
        try {
            Date date = new Date();
            data.setLastChange(new Timestamp(date.getTime()));
            data.setModelCells("{\"edges\":[],\"nodes\":[]}");
            data.setId(UUID.randomUUID().toString());
            data.setVersion(DataVersionUtil.updateVersion(null));
            return plansMapper.insert(data) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String updateBase(Plans data) {
        try {
            Plans plan = plansMapper.selectById(data.getPkid());
            data.setModelCells(plan.getModelCells());
            Date date = new Date();
            data.setLastChange(new Timestamp(date.getTime()));
            data.setVersion(DataVersionUtil.updateVersion(plan.getVersion()));
            return plansMapper.updateById(data) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String delete(Long id) {
        try {
            return plansMapper.deleteById(id) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Plans getByPkid(Long pkid) {
        return plansMapper.selectById(pkid);
    }

    public Plans getBaseByPkid(Long pkid) {
        QueryWrapper<Plans> queryWrapper = new QueryWrapper<Plans>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("pkid"), pkid);
        queryWrapper.select(baseColumns);
        Plans plan = plansMapper.selectOne(queryWrapper);
        return plan;
    }

    public Plans getByDataId(Long projectId, String id) {
        QueryWrapper<Plans> queryWrapper = new QueryWrapper<Plans>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("project_id"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        return plansMapper.selectOne(queryWrapper);
    }

    public Plans getBaseByDataId(Long projectId, String id) {
        QueryWrapper<Plans> queryWrapper = new QueryWrapper<Plans>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("project_id"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        queryWrapper.select(baseColumns);
        Plans plan = plansMapper.selectOne(queryWrapper);
        return plan;
    }

    public PlansExtension getModelCells(Long projectId, String id) {
        QueryWrapper<Plans> queryWrapper = new QueryWrapper<Plans>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("project_id"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        Plans plan = plansMapper.selectOne(queryWrapper);
        PlansExtension planExt = new PlansExtension();
        BeanUtils.copyProperties(plan, planExt);
        planExt.setParentCount(plansMapper.parentCountById(projectId, id));
        return planExt;
    }

    @Transactional
    public String updateModelCells(long projectId, String id, String modelCells) {
        try {
            QueryWrapper<Plans> queryWrapper = new QueryWrapper<Plans>();
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("project_id"), projectId);
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
            Plans plan = plansMapper.selectOne(queryWrapper);
            plan.setModelCells(modelCells);
            Date date = new Date();
            plan.setLastChange(new Timestamp(date.getTime()));
            plan.setVersion(DataVersionUtil.updateVersion(plan.getVersion()));
            plansMapper.updateById(plan);
            return plansMapper.updateById(plan) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public GridData<Plans> getParentGrid(PlansGridFilter filter) {
        QueryWrapper<Plans> queryWrapper = new QueryWrapper<Plans>();
        if (filter.getProjectId() != null)
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
        else
            return new GridData<>();
        if (filter.getId() != null && filter.getId().trim().equals(""))
            queryWrapper.exists("select 1 from jsonb_array_elements(model_cells->'nodes') as j(nodes) where (nodes->>'id')::text = '" + filter.getId() + "'");
        else
            return new GridData<>();
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<Plans> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<Plans> resultPage = plansMapper.selectPage(page, queryWrapper);
        GridData<Plans> gridData = new GridData<Plans>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }

    @Autowired
    IModelFlowsJsonService iModelFlowsJsonService;

    @Autowired
    private IModelFlowsJsonViewService iModelFlowsJsonViewService;

    @Transactional
    public String updateModelFlows(ModelFlowsJsonView data, String option) {
        try {
            Plans plan = new Plans();
            if (data.getProjectId() != null && data.getPlanId() != null)
                plan = getByDataId(data.getProjectId(), data.getPlanId());
            else
                return "err";
            String flowJsonStr = "";
            if (!option.equals("deleteByProcess")) {
                String flowViewJsonStr = "";
                if (!option.equals("delete")) {
                    flowViewJsonStr = data.toJsonString();
                }
                List<ModelFlowsJsonView> flowJsonViewList = iModelFlowsJsonViewService.getList(data.getProjectId(), data.getPlanId(), data.getEdgeSourceId(), data.getEdgeTargetId(), data.getPlanSourceId(), data.getPlanTargetId(),
                        data.getProcessSourceId(), data.getProcessTargetId());
                Iterator<ModelFlowsJsonView> flowJsonViewIterator = flowJsonViewList.iterator();
                while (flowJsonViewIterator.hasNext()) {
                    ModelFlowsJsonView flowJsonViewNext = flowJsonViewIterator.next();
                    if (!(flowJsonViewNext.getFlowSourceId().equals(data.getFlowSourceId()) && flowJsonViewNext.getFlowTargetId().equals(data.getFlowTargetId()))) {
                        flowViewJsonStr = flowViewJsonStr + flowJsonViewNext.toJsonString();
                    }
                }
                if (!(flowViewJsonStr.equals(""))) {
                    flowViewJsonStr = "[" + flowViewJsonStr.substring(0, flowViewJsonStr.length() - 1) + "]";
                    flowJsonStr = flowJsonStr + new ModelFlowsJson().toJsonString(data.getEdgeSourceId(), data.getEdgeTargetId(), data.getPlanSourceId(), data.getPlanTargetId(), data.getProcessSourceId(), data.getProcessTargetId(), flowViewJsonStr);
                }
            }
            List<ModelFlowsJson> flowJsonList = iModelFlowsJsonService.getByPlanPkid(plan.getPkid());
            Iterator<ModelFlowsJson> flowJsonIterator = flowJsonList.iterator();
            while (flowJsonIterator.hasNext()) {
                ModelFlowsJson flowJsonNext = flowJsonIterator.next();
                if (!(flowJsonNext.getEdgeSourceId().equals(data.getEdgeSourceId()) && flowJsonNext.getEdgeTargetId().equals(data.getEdgeTargetId()) && flowJsonNext.getPlanSourceId().equals(data.getPlanSourceId())
                        && flowJsonNext.getPlanTargetId().equals(data.getPlanTargetId()) && flowJsonNext.getProcessSourceId().equals(data.getProcessSourceId()) && flowJsonNext.getProcessTargetId().equals(data.getProcessTargetId()))) {
                    flowJsonStr = flowJsonStr + flowJsonNext.toJsonString();
                }
            }
            if (flowJsonStr.equals(""))
                flowJsonStr = "[]";
            else
                flowJsonStr = "[" + flowJsonStr.substring(0, flowJsonStr.length() - 1) + "]";
            plan.setModelFlows(flowJsonStr);
            Date date = new Date();
            plan.setLastChange(new Timestamp(date.getTime()));
            plan.setVersion(DataVersionUtil.updateVersion(plan.getVersion()));
            return plansMapper.updateById(plan) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
