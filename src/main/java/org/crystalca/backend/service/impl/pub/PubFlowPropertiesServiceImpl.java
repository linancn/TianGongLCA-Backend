package org.crystalca.backend.service.impl.pub;

import org.crystalca.backend.entity.FlowProperties;
import org.crystalca.backend.entity.pub.PubFlowProperties;
import org.crystalca.backend.mapper.pub.PubFlowPropertiesMapper;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.pub.CopyFromPublicDatabaseFilter;
import org.crystalca.backend.model.pub.PubFlowPropertiesGridFilter;
import org.crystalca.backend.service.IFlowPropertiesService;
import org.crystalca.backend.service.pub.IPubFlowPropertiesService;
import org.crystalca.backend.service.pub.IPubUnitGroupsService;
import org.crystalca.backend.util.ColumnNameUtil;

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
 * @author CrystaLCA
 * @since 2022-02-17
 */
@Service
@DS("slave")
public class PubFlowPropertiesServiceImpl extends ServiceImpl<PubFlowPropertiesMapper, PubFlowProperties> implements IPubFlowPropertiesService {
    @Autowired
    PubFlowPropertiesMapper pubFlowPropertiesMapper;

    public GridData<PubFlowProperties> getGrid(PubFlowPropertiesGridFilter filter) {
        QueryWrapper<PubFlowProperties> queryWrapper = new QueryWrapper<PubFlowProperties>();
        if (filter.getDataName() != null && !filter.getDataName().equals(""))
            queryWrapper.like(ColumnNameUtil.toDatabaseName("dataName"), filter.getDataName());
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<PubFlowProperties> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<PubFlowProperties> resultPage = pubFlowPropertiesMapper.selectPage(page, queryWrapper);
        GridData<PubFlowProperties> gridData = new GridData<PubFlowProperties>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }

    @Autowired
    private IFlowPropertiesService iFlowPropertiesService;
    @Autowired
    private IPubUnitGroupsService iPubUnitGroupsService;

    public String copy(CopyFromPublicDatabaseFilter filter) {
        try {
            PubFlowProperties pubData = pubFlowPropertiesMapper.selectById(filter.getPubId());
            FlowProperties priData = new FlowProperties();
            priData.copyPubData(pubData);
            priData.setProjectId(filter.getProjectId());
            if (pubData.getUnitGroupId() != null && !(pubData.getUnitGroupId().trim().equals(""))) {
                CopyFromPublicDatabaseFilter filterUnitGroup = new CopyFromPublicDatabaseFilter();
                filterUnitGroup.setProjectId(filter.getProjectId());
                filterUnitGroup.setPubId(pubData.getUnitGroupId());
                String copyUnitGroup = iPubUnitGroupsService.copy(filterUnitGroup);
                if (!(copyUnitGroup.equals("ok")))
                    return copyUnitGroup;
            }
            return iFlowPropertiesService.insert(priData);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
