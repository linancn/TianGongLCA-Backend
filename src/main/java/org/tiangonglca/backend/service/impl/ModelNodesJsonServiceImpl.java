package org.tiangonglca.backend.service.impl;

import org.tiangonglca.backend.entity.ModelNodesJson;
import org.tiangonglca.backend.mapper.ModelNodesJsonMapper;
import org.tiangonglca.backend.model.TreeNode;
import org.tiangonglca.backend.service.IModelNodesJsonService;
import org.tiangonglca.backend.util.ColumnNameUtil;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-01-20
 */
@Service
public class ModelNodesJsonServiceImpl extends ServiceImpl<ModelNodesJsonMapper, ModelNodesJson> implements IModelNodesJsonService {
    @Autowired
    ModelNodesJsonMapper modelNodesJsonMapper;

    public ModelNodesJson getByDataId(Long projectId, String planId, String nodeId, String nodeType) {
        QueryWrapper<ModelNodesJson> queryWrapper = new QueryWrapper<ModelNodesJson>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("planId"), planId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("nodeId"), nodeId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("nodeType"), nodeType);
        return modelNodesJsonMapper.selectOne(queryWrapper);
    }

    public List<TreeNode> getTree(Long projectId, String parentId, String parentValue) {
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();

        QueryWrapper<ModelNodesJson> queryWrapper = new QueryWrapper<ModelNodesJson>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("planId"), parentId);
        List<ModelNodesJson> modelNodesJson = modelNodesJsonMapper.selectList(queryWrapper);
        if (modelNodesJson.size() > 0) {
            for (ModelNodesJson modelNode : modelNodesJson) {
                TreeNode treeNode = new TreeNode();
                treeNode.setNodeId(modelNode.getNodeId());
                treeNode.setNodeType(modelNode.getNodeType());
                treeNode.setNodeName(modelNode.getNodeName());
                treeNode.setNodeValue("");
                treeNode.setNodeDescription(modelNode.getNodeDescription());
                treeNode.setParentId(parentValue);
                treeNode.setHasChildren(false);
                if (modelNode.getNodeType().equals("plan")) {
                    List<TreeNode> children = getTree(projectId, modelNode.getNodeId(), parentValue + "_" + modelNode.getNodeId());
                    if (children.size() > 0) {
                        treeNode.setHasChildren(true);
                        for (TreeNode child : children) {
                            treeNodes.add(child);
                        }
                    }
                }
                treeNodes.add(treeNode);
            }
        }

        return treeNodes;
    }
}
