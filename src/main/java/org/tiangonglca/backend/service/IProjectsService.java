package org.tiangonglca.backend.service;

import org.tiangonglca.backend.entity.Projects;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.ProjectGridFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-01-07
 */
public interface IProjectsService extends IService<Projects> {
    GridData<Projects> getProjectGrid(ProjectGridFilter filter);

    String create(Projects data);

    String update(Projects data);

    String delete(Long id);

    String star(Long id);
}
