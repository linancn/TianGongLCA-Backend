package org.tiangonglca.backend.service.pub;

import org.tiangonglca.backend.entity.pub.PubUnitGroups;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.pub.PubUnitGroupsGridFilter;
import org.tiangonglca.backend.model.pub.CopyFromPublicDatabaseFilter;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-17
 */
public interface IPubUnitGroupsService extends IService<PubUnitGroups> {
    GridData<PubUnitGroups> getGrid(PubUnitGroupsGridFilter filter);

    String copy(CopyFromPublicDatabaseFilter data);
}
