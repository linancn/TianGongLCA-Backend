package org.crystalca.backend.service;

import org.crystalca.backend.entity.Categories;
import org.crystalca.backend.model.CategoriesGridFilter;
import org.crystalca.backend.model.GridData;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-17
 */
public interface ICategoriesService extends IService<Categories> {
    String create(Categories data);
    String update(Categories data);
    Categories getByDataId(Long projectId, String id);
    Categories getByPkid(Long pkid);
    GridData<Categories> getGrid(CategoriesGridFilter filter);
}
