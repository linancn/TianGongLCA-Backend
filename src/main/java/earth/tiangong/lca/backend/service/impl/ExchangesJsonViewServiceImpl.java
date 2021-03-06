package earth.tiangong.lca.backend.service.impl;

import earth.tiangong.lca.backend.entity.ExchangesJsonView;
import earth.tiangong.lca.backend.mapper.ExchangesJsonViewMapper;
import earth.tiangong.lca.backend.model.ExchangesJsonViewGridFilter;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.service.IExchangesJsonViewService;
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
public class ExchangesJsonViewServiceImpl extends ServiceImpl<ExchangesJsonViewMapper, ExchangesJsonView> implements IExchangesJsonViewService {
    @Autowired
    ExchangesJsonViewMapper exchangesJsonViewMapper;

    public GridData<ExchangesJsonView> getGrid(ExchangesJsonViewGridFilter filter) {
        QueryWrapper<ExchangesJsonView> queryWrapper = new QueryWrapper<ExchangesJsonView>();

        if (filter.getProjectId() != null && filter.getProcessId() != null) {
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("processId"), filter.getProcessId());
        } else {
            GridData<ExchangesJsonView> gridData = new GridData<ExchangesJsonView>();
            return gridData;
        }
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("input"), filter.getInput());

        if (filter.getFlowName() != null && !filter.getFlowName().equals(""))
            queryWrapper.like(ColumnNameUtil.toDatabaseName("flowName"), filter.getFlowName());

        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<ExchangesJsonView> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<ExchangesJsonView> resultPage = exchangesJsonViewMapper.selectPage(page, queryWrapper);
        GridData<ExchangesJsonView> gridData = new GridData<ExchangesJsonView>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }

    public ExchangesJsonView getByDataId(Long projectId, String processId, Long internalId, Boolean input) {
        QueryWrapper<ExchangesJsonView> queryWrapper = new QueryWrapper<ExchangesJsonView>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("processId"), processId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("internalId"), internalId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("input"), input);
        ExchangesJsonView data = exchangesJsonViewMapper.selectOne(queryWrapper);
        return data;
    }
}
