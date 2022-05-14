package org.crystalca.backend.service.impl.pub;

import org.crystalca.backend.entity.FlowPropertiesJson;
import org.crystalca.backend.entity.Flows;
import org.crystalca.backend.entity.pub.PubFlows;
import org.crystalca.backend.mapper.pub.PubFlowsMapper;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.pub.CopyFromPublicDatabaseFilter;
import org.crystalca.backend.model.pub.PubFlowsGridFilter;
import org.crystalca.backend.service.IFlowsService;
import org.crystalca.backend.service.pub.IPubFlowPropertiesService;
import org.crystalca.backend.service.pub.IPubFlowsService;
import org.crystalca.backend.util.ColumnNameUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-17
 */
@Service
@DS("slave")
public class PubFlowsServiceImpl extends ServiceImpl<PubFlowsMapper, PubFlows> implements IPubFlowsService {
    @Autowired
    PubFlowsMapper pubFlowsMapper;

    public GridData<PubFlows> getGrid(PubFlowsGridFilter filter) {
        QueryWrapper<PubFlows> queryWrapper = new QueryWrapper<PubFlows>();
        if (filter.getDataName() != null && !filter.getDataName().equals(""))
            queryWrapper.like(ColumnNameUtil.toDatabaseName("dataName"), filter.getDataName());
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<PubFlows> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<PubFlows> resultPage = pubFlowsMapper.selectPage(page, queryWrapper);
        GridData<PubFlows> gridData = new GridData<PubFlows>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }

    @Autowired
    private IFlowsService iFlowsService;
    @Autowired
    private IPubFlowPropertiesService iPubFlowPropertiesService;

    public String copy(CopyFromPublicDatabaseFilter filter) {
        try {
            PubFlows pubFlow = pubFlowsMapper.selectById(filter.getPubId());
            Flows flow = new Flows();
            flow.copyPubData(pubFlow);
            flow.setProjectId(filter.getProjectId());
            String flowProperties = pubFlow.getFlowProperties();
            String flowPropertiesJsonStr = "";
            if (flowProperties != null && !(flowProperties.trim().equals(""))) {
                JSONArray flowPropertyJsonArray = new JSONArray(flowProperties);
                if (flowPropertyJsonArray.length() > 0) {
                    for (int i = 0; i < flowPropertyJsonArray.length(); i++) {
                        FlowPropertiesJson flowPropertiesJson = new FlowPropertiesJson();
                        JSONObject flowPropertyJsonObject = flowPropertyJsonArray.getJSONObject(i);
                        JSONObject flowProperty = flowPropertyJsonObject.getJSONObject("flowProperty");
                        String flowPropertyId = flowProperty.getString("@id");
                        flowPropertiesJson.setFlowPropertyId(flowPropertyId);
                        flowPropertiesJson.setConversionFactor(flowPropertyJsonObject.has("conversionFactor") ? flowPropertyJsonObject.getDouble("conversionFactor") : null);
                        flowPropertiesJson.setReferenceFlowProperty(flowPropertyJsonObject.has("referenceFlowProperty") ? flowPropertyJsonObject.getBoolean("referenceFlowProperty") : null);
                        flowPropertiesJsonStr = flowPropertiesJsonStr + flowPropertiesJson.toJsonString();

                        CopyFromPublicDatabaseFilter filterFlowProperty = new CopyFromPublicDatabaseFilter();
                        filterFlowProperty.setProjectId(filter.getProjectId());
                        filterFlowProperty.setPubId(flowPropertyId);
                        String copyFlowProperty = iPubFlowPropertiesService.copy(filterFlowProperty);
                        if (!(copyFlowProperty.equals("ok")))
                            return copyFlowProperty;
                    }
                }
            }
            if (flowPropertiesJsonStr.equals(""))
                flowPropertiesJsonStr = "[]";
            else
                flowPropertiesJsonStr = "[" + flowPropertiesJsonStr.substring(0, flowPropertiesJsonStr.length() - 1) + "]";
            flow.setFlowProperties(flowPropertiesJsonStr);
            return iFlowsService.insert(flow);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
