package org.crystalca.backend.service.impl;

import org.crystalca.backend.entity.ModelEdgesJson;
import org.crystalca.backend.mapper.ModelEdgesJsonMapper;
import org.crystalca.backend.service.IModelEdgesJsonService;
import org.crystalca.backend.util.ColumnNameUtil;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelEdgesJsonServiceImpl extends ServiceImpl<ModelEdgesJsonMapper, ModelEdgesJson> implements IModelEdgesJsonService {
    @Autowired
    ModelEdgesJsonMapper modelEdgesJsonMapper;

    public List<ModelEdgesJson> getByPlanPkid(Long planPkid) {
        QueryWrapper<ModelEdgesJson> queryWrapper = new QueryWrapper<ModelEdgesJson>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("planPkid"), planPkid);
        return modelEdgesJsonMapper.selectList(queryWrapper);
    }
}
