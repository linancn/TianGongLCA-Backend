package earth.tiangong.lca.backend.service.impl;

import earth.tiangong.lca.backend.entity.FlowPropertiesJson;
import earth.tiangong.lca.backend.mapper.FlowPropertiesJsonMapper;
import earth.tiangong.lca.backend.service.IFlowPropertiesJsonService;
import earth.tiangong.lca.backend.util.ColumnNameUtil;

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
 * @author TianGongLCA
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
