package earth.tiangong.lca.backend.service.impl;

import earth.tiangong.lca.backend.entity.ModelEdgesJson;
import earth.tiangong.lca.backend.mapper.ModelEdgesJsonMapper;
import earth.tiangong.lca.backend.service.IModelEdgesJsonService;
import earth.tiangong.lca.backend.util.ColumnNameUtil;

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
