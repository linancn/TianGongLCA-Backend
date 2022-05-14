package org.crystalca.backend.service.impl;

import org.crystalca.backend.entity.Categories;
import org.crystalca.backend.mapper.CategoriesMapper;
import org.crystalca.backend.model.CategoriesGridFilter;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.service.ICategoriesService;
import org.crystalca.backend.util.ColumnNameUtil;
import org.crystalca.backend.util.DataVersionUtil;

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
 * @author CrystaLCA
 * @since 2022-02-17
 */
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories> implements ICategoriesService {
    @Autowired
    CategoriesMapper categoriesMapper;

    @Transactional
    public String create(Categories data) {
        try {
            Date date = new Date();
            data.setLastChange(new Timestamp(date.getTime()));
            data.setId(UUID.randomUUID().toString());
            data.setVersion(DataVersionUtil.newVersion());
            return categoriesMapper.insert(data) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String update(Categories data) {
        try {
            Categories category = categoriesMapper.selectById(data.getPkid());
            Date date = new Date();
            data.setLastChange(new Timestamp(date.getTime()));
            data.setVersion(DataVersionUtil.updateVersion(category.getVersion()));
            return categoriesMapper.updateById(data) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Categories getByDataId(Long projectId, String id) {
        QueryWrapper<Categories> queryWrapper = new QueryWrapper<Categories>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        return categoriesMapper.selectOne(queryWrapper);
    }

    public Categories getByPkid(Long pkid) {
        return categoriesMapper.selectById(pkid);
    }

    public GridData<Categories> getGrid(CategoriesGridFilter filter) {
        QueryWrapper<Categories> queryWrapper = new QueryWrapper<Categories>();
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
        Page<Categories> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<Categories> resultPage = categoriesMapper.selectPage(page, queryWrapper);
        GridData<Categories> gridData = new GridData<Categories>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }
}
