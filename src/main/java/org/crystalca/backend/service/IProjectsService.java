package org.crystalca.backend.service;

import org.crystalca.backend.entity.Projects;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.ProjectGridFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-01-07
 */
public interface IProjectsService extends IService<Projects> {
    GridData<Projects> getProjectGrid(ProjectGridFilter filter);

    String create(Projects data);

    String update(Projects data);

    String delete(Long id);

    String star(Long id);
}
