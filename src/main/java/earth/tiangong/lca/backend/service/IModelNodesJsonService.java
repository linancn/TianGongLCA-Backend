package earth.tiangong.lca.backend.service;

import earth.tiangong.lca.backend.entity.ModelNodesJson;
import earth.tiangong.lca.backend.model.TreeNode;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-01-20
 */
public interface IModelNodesJsonService extends IService<ModelNodesJson> {
    ModelNodesJson getByDataId(Long projectId, String planId, String nodeId, String nodeType);

    List<TreeNode> getTree(Long projectId, String parentId, String parentValue);
}
