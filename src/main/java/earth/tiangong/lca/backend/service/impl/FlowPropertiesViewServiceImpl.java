package earth.tiangong.lca.backend.service.impl;

import earth.tiangong.lca.backend.entity.FlowPropertiesView;
import earth.tiangong.lca.backend.mapper.FlowPropertiesViewMapper;
import earth.tiangong.lca.backend.model.FlowPropertiesViewGridFilter;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.service.IFlowPropertiesViewService;
import earth.tiangong.lca.backend.util.ColumnNameUtil;

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
public class FlowPropertiesViewServiceImpl extends ServiceImpl<FlowPropertiesViewMapper, FlowPropertiesView> implements IFlowPropertiesViewService {
    @Autowired
    FlowPropertiesViewMapper flowPropertiesViewMapper;

    public FlowPropertiesView getByDataId(Long projectId, String id) {
        QueryWrapper<FlowPropertiesView> queryWrapper = new QueryWrapper<FlowPropertiesView>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        return flowPropertiesViewMapper.selectOne(queryWrapper);
    }

    public FlowPropertiesView getByPkid(Long pkid) {
        return flowPropertiesViewMapper.selectById(pkid);
    }

    public GridData<FlowPropertiesView> getGrid(FlowPropertiesViewGridFilter filter) {
        QueryWrapper<FlowPropertiesView> queryWrapper = new QueryWrapper<FlowPropertiesView>();
        if (filter.getProjectId() != null) {
            if (filter.getOtherProject())
                queryWrapper.ne(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
            else
                queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
        } else
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
        Page<FlowPropertiesView> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<FlowPropertiesView> resultPage = flowPropertiesViewMapper.selectPage(page, queryWrapper);
        GridData<FlowPropertiesView> gridData = new GridData<FlowPropertiesView>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }
}
