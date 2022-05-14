package org.crystalca.backend.service.impl;

import org.crystalca.backend.entity.Projects;
import org.crystalca.backend.mapper.ProjectsMapper;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.ProjectGridFilter;
import org.crystalca.backend.service.IProjectsService;
import org.crystalca.backend.util.ColumnNameUtil;

import java.sql.Timestamp;
import java.util.Date;

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
 * @since 2022-01-07
 */
@Service
public class ProjectsServiceImpl extends ServiceImpl<ProjectsMapper, Projects> implements IProjectsService {
    @Autowired
    ProjectsMapper projectsMapper;

    public GridData<Projects> getProjectGrid(ProjectGridFilter filter) {
        QueryWrapper<Projects> queryWrapper = new QueryWrapper<Projects>();
        if (filter.getName() != null && !filter.getName().equals(""))
            queryWrapper.like(ColumnNameUtil.toDatabaseName("name"), filter.getName());
        if (filter.getStar() != null)
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("star"), filter.getStar());
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<Projects> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<Projects> projectPage = projectsMapper.selectPage(page, queryWrapper);
        GridData<Projects> gridData = new GridData<Projects>(projectPage.getRecords(), projectPage.getTotal(), projectPage.getCurrent(), projectPage.getSize(), true);
        return gridData;
    }

    @Transactional
    public String create(Projects project) {
        try {
            Date date = new Date();
            project.setCreateTime(new Timestamp(date.getTime()));
            project.setLastChange(new Timestamp(date.getTime()));
            return projectsMapper.insert(project) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String update(Projects project) {
        try {
            Date date = new Date();
            project.setLastChange(new Timestamp(date.getTime()));
            return projectsMapper.updateById(project) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String delete(Long id) {
        try {
            return projectsMapper.deleteById(id) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String star(Long id) {
        try {
            Projects project = projectsMapper.selectById(id);
            project.setStar(project.getStar() == null ? true : !project.getStar());
            Date date = new Date();
            project.setLastChange(new Timestamp(date.getTime()));
            return projectsMapper.updateById(project) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
