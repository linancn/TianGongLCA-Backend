package earth.tiangong.lca.backend.service.pub;

import earth.tiangong.lca.backend.entity.pub.PubUnitGroups;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.pub.PubUnitGroupsGridFilter;
import earth.tiangong.lca.backend.model.pub.CopyFromPublicDatabaseFilter;

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
