package org.crystalca.backend.service.pub;

import org.crystalca.backend.entity.pub.PubUnitGroups;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.pub.PubUnitGroupsGridFilter;
import org.crystalca.backend.model.pub.CopyFromPublicDatabaseFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-17
 */
public interface IPubUnitGroupsService extends IService<PubUnitGroups> {
    GridData<PubUnitGroups> getGrid(PubUnitGroupsGridFilter filter);

    String copy(CopyFromPublicDatabaseFilter data);
}
