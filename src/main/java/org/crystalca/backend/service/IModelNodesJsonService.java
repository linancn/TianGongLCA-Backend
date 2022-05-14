package org.crystalca.backend.service;

import org.crystalca.backend.entity.ModelNodesJson;
import org.crystalca.backend.model.TreeNode;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-01-20
 */
public interface IModelNodesJsonService extends IService<ModelNodesJson> {
    ModelNodesJson getByDataId(Long projectId, String planId, String nodeId, String nodeType);

    List<TreeNode> getTree(Long projectId, String parentId, String parentValue);
}
