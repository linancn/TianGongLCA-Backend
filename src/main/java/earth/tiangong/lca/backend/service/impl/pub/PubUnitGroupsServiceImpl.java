package earth.tiangong.lca.backend.service.impl.pub;

import earth.tiangong.lca.backend.entity.UnitGroups;
import earth.tiangong.lca.backend.entity.pub.PubUnitGroups;
import earth.tiangong.lca.backend.mapper.pub.PubUnitGroupsMapper;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.pub.PubUnitGroupsGridFilter;
import earth.tiangong.lca.backend.model.pub.CopyFromPublicDatabaseFilter;
import earth.tiangong.lca.backend.service.IUnitGroupsService;
import earth.tiangong.lca.backend.service.pub.IPubUnitGroupsService;
import earth.tiangong.lca.backend.util.ColumnNameUtil;

import com.baomidou.dynamic.datasource.annotation.DS;
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
 * @since 2022-02-17
 */
@Service
@DS("slave")
public class PubUnitGroupsServiceImpl extends ServiceImpl<PubUnitGroupsMapper, PubUnitGroups> implements IPubUnitGroupsService {
    @Autowired
    PubUnitGroupsMapper pubUnitGroupsMapper;

    public GridData<PubUnitGroups> getGrid(PubUnitGroupsGridFilter filter) {
        QueryWrapper<PubUnitGroups> queryWrapper = new QueryWrapper<PubUnitGroups>();
        if (filter.getDataName() != null && !filter.getDataName().equals(""))
            queryWrapper.like(ColumnNameUtil.toDatabaseName("dataName"), filter.getDataName());
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<PubUnitGroups> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<PubUnitGroups> resultPage = pubUnitGroupsMapper.selectPage(page, queryWrapper);
        GridData<PubUnitGroups> gridData = new GridData<PubUnitGroups>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }

    @Autowired
    private IUnitGroupsService iUnitGroupsService;

    public String copy(CopyFromPublicDatabaseFilter filter) {
        try {
            PubUnitGroups pubUnitGroup = pubUnitGroupsMapper.selectById(filter.getPubId());
            UnitGroups unitGroup = new UnitGroups();
            unitGroup.copyPubData(pubUnitGroup);
            unitGroup.setProjectId(filter.getProjectId());
            return iUnitGroupsService.insert(unitGroup);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
