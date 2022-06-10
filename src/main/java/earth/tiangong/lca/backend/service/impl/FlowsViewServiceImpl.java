package earth.tiangong.lca.backend.service.impl;

import earth.tiangong.lca.backend.entity.FlowsView;
import earth.tiangong.lca.backend.mapper.FlowsViewMapper;
import earth.tiangong.lca.backend.model.FlowsViewGridFilter;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.service.IFlowsViewService;
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
public class FlowsViewServiceImpl extends ServiceImpl<FlowsViewMapper, FlowsView> implements IFlowsViewService {
    @Autowired
    FlowsViewMapper flowsViewMapper;

    public FlowsView getByPkid(Long pkid) {
        return flowsViewMapper.selectById(pkid);
    }

    public FlowsView getByDataId(Long projectId, String id) {
        QueryWrapper<FlowsView> queryWrapper = new QueryWrapper<FlowsView>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        return flowsViewMapper.selectOne(queryWrapper);
    }

    public GridData<FlowsView> getGrid(FlowsViewGridFilter filter) {
        QueryWrapper<FlowsView> queryWrapper = new QueryWrapper<FlowsView>();
        if (filter.getProjectId() != null) {
            if (filter.getOtherProject())
                queryWrapper.ne(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
            else
                queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
        } else
            return new GridData<>();
        if (filter.getDataName() != null && !filter.getDataName().equals(""))
            queryWrapper.like(ColumnNameUtil.toDatabaseName("dataName"), filter.getDataName());
        if (filter.getFlowType() != null && !filter.getFlowType().equals(""))
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("flowType"), filter.getFlowType());
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<FlowsView> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<FlowsView> resultPage = flowsViewMapper.selectPage(page, queryWrapper);
        GridData<FlowsView> gridData = new GridData<FlowsView>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }
}
