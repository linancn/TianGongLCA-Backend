package earth.tiangong.lca.backend.service.impl;

import earth.tiangong.lca.backend.entity.Locations;
import earth.tiangong.lca.backend.mapper.LocationsMapper;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.LocationsGridFilter;
import earth.tiangong.lca.backend.service.ILocationsService;
import earth.tiangong.lca.backend.util.ColumnNameUtil;
import earth.tiangong.lca.backend.util.DataVersionUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-17
 */
@Service
public class LocationsServiceImpl extends ServiceImpl<LocationsMapper, Locations> implements ILocationsService {
    @Autowired
    LocationsMapper locationsMapper;

    @Transactional
    public String create(Locations data) {
        try {
            Date date = new Date();
            data.setLastChange(new Timestamp(date.getTime()));
            data.setId(UUID.randomUUID().toString());
            data.setVersion(DataVersionUtil.newVersion());
            return locationsMapper.insert(data) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String update(Locations data) {
        try {
            Locations location = locationsMapper.selectById(data.getPkid());
            Date date = new Date();
            data.setLastChange(new Timestamp(date.getTime()));
            data.setVersion(DataVersionUtil.updateVersion(location.getVersion()));
            return locationsMapper.updateById(data) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Locations getByDataId(Long projectId, String id) {
        QueryWrapper<Locations> queryWrapper = new QueryWrapper<Locations>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        return locationsMapper.selectOne(queryWrapper);
    }

    public Locations getByPkid(Long pkid) {
        return locationsMapper.selectById(pkid);
    }

    public GridData<Locations> getGrid(LocationsGridFilter filter) {
        QueryWrapper<Locations> queryWrapper = new QueryWrapper<Locations>();
        if (filter.getProjectId() != null)
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
        if (filter.getDataName() != null && !filter.getDataName().equals(""))
            queryWrapper.like(ColumnNameUtil.toDatabaseName("dataName"), filter.getDataName());
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<Locations> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<Locations> resultPage = locationsMapper.selectPage(page, queryWrapper);
        GridData<Locations> gridData = new GridData<Locations>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }
}
