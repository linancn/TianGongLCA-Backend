package org.crystalca.backend.service.impl;

import org.crystalca.backend.entity.FlowPropertiesJson;
import org.crystalca.backend.mapper.FlowPropertiesJsonMapper;
import org.crystalca.backend.service.IFlowPropertiesJsonService;
import org.crystalca.backend.util.ColumnNameUtil;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-13
 */
@Service
public class FlowPropertiesJsonServiceImpl extends ServiceImpl<FlowPropertiesJsonMapper, FlowPropertiesJson> implements IFlowPropertiesJsonService {
    @Autowired
    FlowPropertiesJsonMapper flowPropertiesJsonMapper;

    public List<FlowPropertiesJson> getListByDataId(Long projectId, String flowId) {
        QueryWrapper<FlowPropertiesJson> queryWrapper = new QueryWrapper<FlowPropertiesJson>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("flowId"), flowId);
        return flowPropertiesJsonMapper.selectList(queryWrapper);
    }
}
