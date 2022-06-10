package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.Projects;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.ProjectGridFilter;

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
