package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.Categories;
import earth.tiangong.lca.backend.model.CategoriesGridFilter;
import earth.tiangong.lca.backend.model.GridData;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-17
 */
public interface ICategoriesService extends IService<Categories> {
    String create(Categories data);
    String update(Categories data);
    Categories getByDataId(Long projectId, String id);
    Categories getByPkid(Long pkid);
    GridData<Categories> getGrid(CategoriesGridFilter filter);
}
