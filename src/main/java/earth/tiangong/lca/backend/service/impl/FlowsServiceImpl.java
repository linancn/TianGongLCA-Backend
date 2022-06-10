package earth.tiangong.lca.backend.service.impl;

import earth.tiangong.lca.backend.entity.FlowPropertiesJson;
import earth.tiangong.lca.backend.entity.Flows;
import earth.tiangong.lca.backend.mapper.FlowsMapper;
import earth.tiangong.lca.backend.model.CopyFromOtherProjectsFilter;
import earth.tiangong.lca.backend.service.IFlowPropertiesJsonService;
import earth.tiangong.lca.backend.service.IFlowPropertiesService;
import earth.tiangong.lca.backend.service.IFlowsService;
import earth.tiangong.lca.backend.util.ColumnNameUtil;
import earth.tiangong.lca.backend.util.DataVersionUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-13
 */
@Service
@DS("master")
public class FlowsServiceImpl extends ServiceImpl<FlowsMapper, Flows> implements IFlowsService {
    @Autowired
    FlowsMapper flowsMapper;

    public Flows getByPkid(Long pkid) {
        return flowsMapper.selectById(pkid);
    }

    public Flows getByDataId(Long projectId, String id) {
        QueryWrapper<Flows> queryWrapper = new QueryWrapper<Flows>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        return flowsMapper.selectOne(queryWrapper);
    }

    @Transactional
    public String insert(Flows data) {
        try {
            if (data.getId() == null) {
                data.setId(UUID.randomUUID().toString());
                return flowsMapper.insert(data) == 1 ? "ok" : "err";
            } else {
                QueryWrapper<Flows> queryWrapper = new QueryWrapper<Flows>();
                queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), data.getProjectId());
                queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), data.getId());
                if (flowsMapper.selectCount(queryWrapper) == 0) {
                    return flowsMapper.insert(data) == 1 ? "ok" : "err";
                } else {
                    return "ok";
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String create(Flows data) {
        try {
            Date date = new Date();
            data.setLastChange(new Timestamp(date.getTime()));
            data.setVersion(DataVersionUtil.newVersion());
            return insert(data);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Autowired
    private IFlowPropertiesService iFlowPropertiesService;

    @Transactional
    public String copy(CopyFromOtherProjectsFilter filter) {
        try {
            QueryWrapper<Flows> queryWrapper = new QueryWrapper<Flows>();
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getOthProjectId());
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), filter.getOthId());
            Flows flow = flowsMapper.selectOne(queryWrapper);
            flow.setProjectId(filter.getProjectId());
            String flowProperties = flow.getFlowProperties();
            if (flowProperties != null && !(flowProperties.trim().equals(""))) {
                JSONArray flowPropertyJsonArray = new JSONArray(flowProperties);
                if (flowPropertyJsonArray.length() > 0) {
                    for (int i = 0; i < flowPropertyJsonArray.length(); i++) {
                        JSONObject flowPropertyJsonObject = flowPropertyJsonArray.getJSONObject(i);
                        JSONObject flowProperty = flowPropertyJsonObject.getJSONObject("flowProperty");
                        String flowPropertyId = flowProperty.getString("@id");
                        CopyFromOtherProjectsFilter filterFlowProperty = new CopyFromOtherProjectsFilter();
                        filterFlowProperty.setProjectId(filter.getProjectId());
                        filterFlowProperty.setOthProjectId(filter.getOthProjectId());
                        filterFlowProperty.setOthId(flowPropertyId);
                        String copyFlowProperty = iFlowPropertiesService.copy(filterFlowProperty);
                        if (!(copyFlowProperty.equals("ok")))
                            return copyFlowProperty;
                    }
                }
            }
            return insert(flow);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String updateBase(Flows data) {
        try {
            Flows flow = flowsMapper.selectById(data.getPkid());
            data.setLocationId(flow.getLocationId());
            data.setCategoryId(flow.getCategoryId());
            Date date = new Date();
            data.setLastChange(new Timestamp(date.getTime()));
            data.setVersion(DataVersionUtil.updateVersion(flow.getVersion()));
            return flowsMapper.updateById(data) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String updateLocation(Flows data) {
        try {
            Flows flow = getByDataId(data.getProjectId(), data.getId());
            if (data.getLocationId() == "")
                flow.setLocationId(null);
            else
                flow.setLocationId(data.getLocationId());
            Date date = new Date();
            flow.setLastChange(new Timestamp(date.getTime()));
            flow.setVersion(DataVersionUtil.updateVersion(flow.getVersion()));
            return flowsMapper.updateById(flow) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String updateCategory(Flows data) {
        try {
            Flows flow = getByDataId(data.getProjectId(), data.getId());
            if (data.getCategoryId() == "")
                flow.setCategoryId(null);
            else
                flow.setCategoryId(data.getCategoryId());
            Date date = new Date();
            flow.setLastChange(new Timestamp(date.getTime()));
            flow.setVersion(DataVersionUtil.updateVersion(flow.getVersion()));
            return flowsMapper.updateById(flow) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Autowired
    private IFlowPropertiesJsonService iFlowPropertiesJsonService;

    @Transactional
    public String updatePropertyJson(String propertyId, FlowPropertiesJson data, String option) {
        try {
            Flows flow = getByDataId(data.getProjectId(), data.getFlowId());
            double cf = data.getConversionFactor();
            String json = "";
            if (!option.equals("delete")) {
                if (data.getReferenceFlowProperty())
                    json = data.toJsonString(true, cf);
                else
                    json = data.toJsonString();
            }
            List<FlowPropertiesJson> list = iFlowPropertiesJsonService.getListByDataId(data.getProjectId(), data.getFlowId());
            Iterator<FlowPropertiesJson> it = list.iterator();
            while (it.hasNext()) {
                FlowPropertiesJson next = it.next();
                if (!(next.getFlowPropertyId().equals(propertyId))) {
                    if (data.getReferenceFlowProperty())
                        json = json + next.toJsonString(false, cf);
                    else
                        json = json + next.toJsonString();
                }
            }
            if (json.equals(""))
                json = "[]";
            else
                json = "[" + json.substring(0, json.length() - 1) + "]";
            flow.setFlowProperties(json);
            Date date = new Date();
            flow.setLastChange(new Timestamp(date.getTime()));
            flow.setVersion(DataVersionUtil.updateVersion(flow.getVersion()));
            return flowsMapper.updateById(flow) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
