package org.crystalca.backend.service.impl;

import org.crystalca.backend.entity.FlowProperties;
import org.crystalca.backend.mapper.FlowPropertiesMapper;
import org.crystalca.backend.model.CopyFromOtherProjectsFilter;
import org.crystalca.backend.service.IFlowPropertiesService;
import org.crystalca.backend.service.IUnitGroupsService;
import org.crystalca.backend.util.ColumnNameUtil;
import org.crystalca.backend.util.DataVersionUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @since 2022-02-17
 */
@Service
@DS("master")
public class FlowPropertiesServiceImpl extends ServiceImpl<FlowPropertiesMapper, FlowProperties> implements IFlowPropertiesService {
    @Autowired
    FlowPropertiesMapper flowPropertiesMapper;

    public FlowProperties getByDataId(Long projectId, String id) {
        QueryWrapper<FlowProperties> queryWrapper = new QueryWrapper<FlowProperties>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        return flowPropertiesMapper.selectOne(queryWrapper);
    }

    @Transactional
    public String insert(FlowProperties data) {
        try {
            if (data.getId() == null) {
                data.setId(UUID.randomUUID().toString());
                return flowPropertiesMapper.insert(data) == 1 ? "ok" : "err";
            } else {
                QueryWrapper<FlowProperties> queryWrapper = new QueryWrapper<FlowProperties>();
                queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), data.getProjectId());
                queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), data.getId());
                if (flowPropertiesMapper.selectCount(queryWrapper) == 0) {
                    return flowPropertiesMapper.insert(data) == 1 ? "ok" : "err";
                } else {
                    return "ok";
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String create(FlowProperties data) {
        try {
            Date date = new Date();
            data.setLastChange(new Timestamp(date.getTime()));
            data.setVersion(DataVersionUtil.newVersion());
            return insert(data);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String updateBase(FlowProperties data) {
        try {
            FlowProperties flowProperty = flowPropertiesMapper.selectById(data.getPkid());
            data.setCategoryId(flowProperty.getCategoryId());
            data.setUnitGroupId(flowProperty.getUnitGroupId());
            Date date = new Date();
            data.setLastChange(new Timestamp(date.getTime()));
            data.setVersion(DataVersionUtil.updateVersion(flowProperty.getVersion()));
            return flowPropertiesMapper.updateById(data) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Autowired
    private IUnitGroupsService iUnitGroupsService;

    @Transactional
    public String copy(CopyFromOtherProjectsFilter filter) {
        try {
            QueryWrapper<FlowProperties> queryWrapper = new QueryWrapper<FlowProperties>();
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getOthProjectId());
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), filter.getOthId());
            FlowProperties flowProperty = flowPropertiesMapper.selectOne(queryWrapper);
            flowProperty.setProjectId(filter.getProjectId());
            if (flowProperty.getUnitGroupId() != null && !(flowProperty.getUnitGroupId().trim().equals(""))) {
                CopyFromOtherProjectsFilter filterUnitGroup = new CopyFromOtherProjectsFilter();
                filterUnitGroup.setProjectId(filter.getProjectId());
                filterUnitGroup.setOthProjectId(filter.getOthProjectId());
                filterUnitGroup.setOthId(flowProperty.getUnitGroupId());
                String copyUnitGroup = iUnitGroupsService.copy(filterUnitGroup);
                if (!(copyUnitGroup.equals("ok")))
                    return copyUnitGroup;
            }
            return insert(flowProperty);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String updateCategory(FlowProperties data) {
        try {
            FlowProperties flowProperty = getByDataId(data.getProjectId(), data.getId());
            if (data.getCategoryId() == "")
                flowProperty.setCategoryId(null);
            else
                flowProperty.setCategoryId(data.getCategoryId());
            Date date = new Date();
            flowProperty.setLastChange(new Timestamp(date.getTime()));
            flowProperty.setVersion(DataVersionUtil.updateVersion(flowProperty.getVersion()));
            return flowPropertiesMapper.updateById(flowProperty) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String updateUnitGroup(FlowProperties data) {
        try {
            FlowProperties flowProperty = getByDataId(data.getProjectId(), data.getId());
            if (data.getUnitGroupId() == "")
                flowProperty.setUnitGroupId(null);
            else
                flowProperty.setUnitGroupId(data.getUnitGroupId());
            Date date = new Date();
            flowProperty.setLastChange(new Timestamp(date.getTime()));
            flowProperty.setVersion(DataVersionUtil.updateVersion(flowProperty.getVersion()));
            return flowPropertiesMapper.updateById(flowProperty) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
