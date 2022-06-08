package org.tiangonglca.backend.service.impl;

import org.tiangonglca.backend.entity.ExchangesJson;
import org.tiangonglca.backend.entity.ParametersJson;
import org.tiangonglca.backend.entity.Processes;
import org.tiangonglca.backend.mapper.ProcessesMapper;
import org.tiangonglca.backend.model.CopyFromOtherProjectsFilter;
import org.tiangonglca.backend.model.GridData;
import org.tiangonglca.backend.model.ProcessesGridFilter;
import org.tiangonglca.backend.service.IExchangesJsonService;
import org.tiangonglca.backend.service.IFlowsService;
import org.tiangonglca.backend.service.IParametersJsonService;
import org.tiangonglca.backend.service.IProcessesService;
import org.tiangonglca.backend.util.ColumnNameUtil;
import org.tiangonglca.backend.util.DataVersionUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Iterator;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @since 2022-03-13
 */
@Service
@DS("master")
public class ProcessesServiceImpl extends ServiceImpl<ProcessesMapper, Processes> implements IProcessesService {
    @Autowired
    ProcessesMapper processesMapper;

    @Transactional
    public String insert(Processes data) {
        try {
            if (data.getId() == null) {
                data.setId(UUID.randomUUID().toString());
                return processesMapper.insert(data) == 1 ? "ok" : "err";
            } else {
                QueryWrapper<Processes> queryWrapper = new QueryWrapper<Processes>();
                queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), data.getProjectId());
                queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), data.getId());
                if (processesMapper.selectCount(queryWrapper) == 0) {
                    return processesMapper.insert(data) == 1 ? "ok" : "err";
                } else {
                    return "ok";
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String create(Processes data) {
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
    private IFlowsService iFlowsService;

    @Transactional
    public String copy(CopyFromOtherProjectsFilter filter) {
        try {
            QueryWrapper<Processes> queryWrapper = new QueryWrapper<Processes>();
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getOthProjectId());
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), filter.getOthId());
            Processes process = processesMapper.selectOne(queryWrapper);
            process.setProjectId(filter.getProjectId());
            String exchanges = process.getExchanges();
            if (exchanges != null && !(exchanges.trim().equals(""))) {
                JSONArray exchangeJsonArray = new JSONArray(exchanges);
                if (exchangeJsonArray.length() > 0) {
                    for (int i = 0; i < exchangeJsonArray.length(); i++) {
                        JSONObject exchangeJsonObject = exchangeJsonArray.getJSONObject(i);
                        JSONObject flow = exchangeJsonObject.getJSONObject("flow");
                        String flowId = flow.getString("@id");
                        CopyFromOtherProjectsFilter filterFlow = new CopyFromOtherProjectsFilter();
                        filterFlow.setProjectId(filter.getProjectId());
                        filterFlow.setOthProjectId(filter.getOthProjectId());
                        filterFlow.setOthId(flowId);
                        String copyFlow = iFlowsService.copy(filterFlow);
                        if (!(copyFlow.equals("ok")))
                            return copyFlow;
                    }
                }
            }
            return insert(process);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String updateBase(Processes data) {
        try {
            Processes process = processesMapper.selectById(data.getPkid());
            Date date = new Date();
            data.setLastChange(new Timestamp(date.getTime()));
            data.setVersion(DataVersionUtil.updateVersion(process.getVersion()));
            return processesMapper.updateById(data) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public GridData<Processes> getGrid(ProcessesGridFilter filter) {
        QueryWrapper<Processes> queryWrapper = new QueryWrapper<Processes>();
        if (filter.getProjectId() != null) {
            if (filter.getOtherProject())
                queryWrapper.ne(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
            else
                queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getProjectId());
        } else
            return new GridData<>();
        if (filter.getDataName() != null && !filter.getDataName().equals(""))
            queryWrapper.like(ColumnNameUtil.toDatabaseName("dataName"), filter.getDataName());
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<Processes> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<Processes> resultPage = processesMapper.selectPage(page, queryWrapper);
        GridData<Processes> gridData = new GridData<Processes>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }

    public Processes getByDataId(Long projectId, String id) {
        QueryWrapper<Processes> queryWrapper = new QueryWrapper<Processes>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("project_id"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        Processes data = processesMapper.selectOne(queryWrapper);
        return data;
    }

    public Processes getByPkid(Long pkid) {
        return processesMapper.selectById(pkid);
    }

    @Autowired
    private IParametersJsonService iParametersJsonService;

    @Transactional
    public String updateParametersJson(ParametersJson data, String option) {
        try {
            Processes process = getByDataId(data.getProjectId(), data.getProcessId());
            String json = "";

            if (!option.equals("delete")) {
                if (option.equals("add")) {
                    data.setId(UUID.randomUUID().toString());
                    data.setVersion(DataVersionUtil.newVersion());
                } else {
                    ParametersJson parametersJson = iParametersJsonService.getByDataId(data.getProjectId(), data.getProcessId(), data.getId());
                    data.setVersion(DataVersionUtil.updateVersion(parametersJson.getVersion()));
                }
                json = data.toJsonString();
            }

            List<ParametersJson> list = iParametersJsonService.getListByDataId(data.getProjectId(), data.getProcessId());
            Iterator<ParametersJson> it = list.iterator();
            while (it.hasNext()) {
                ParametersJson next = it.next();
                if (!(next.getId().equals(data.getId())))
                    json = json + next.toJsonString();
            }
            if (json.equals(""))
                json = "[]";
            else
                json = "[" + json.substring(0, json.length() - 1) + "]";
            process.setParameters(json);
            Date date = new Date();
            process.setLastChange(new Timestamp(date.getTime()));
            process.setVersion(DataVersionUtil.updateVersion(process.getVersion()));
            return processesMapper.updateById(process) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Autowired
    private IExchangesJsonService iExchangesJsonService;

    @Transactional
    public String updateExchangesJson(ExchangesJson data, String option) {
        try {
            Processes process = getByDataId(data.getProjectId(), data.getProcessId());
            String json = "";
            if (!option.equals("delete")) {
                if (option.equals("add")) {
                    process.setLastInternalId(process.getLastInternalId() + 1);
                    data.setInternalId(process.getLastInternalId());
                }
                json = data.toJsonString();
            }
            List<ExchangesJson> list = iExchangesJsonService.getListByDataId(data.getProjectId(), data.getProcessId());
            Iterator<ExchangesJson> it = list.iterator();
            while (it.hasNext()) {
                ExchangesJson next = it.next();
                if (!(next.getInternalId().equals(data.getInternalId()) && next.getFlowId().equals(data.getFlowId()) && next.getInput().equals(data.getInput())))
                    json = json + next.toJsonString();
            }
            if (json.equals(""))
                json = "[]";
            else
                json = "[" + json.substring(0, json.length() - 1) + "]";
            process.setExchanges(json);
            Date date = new Date();
            process.setLastChange(new Timestamp(date.getTime()));
            process.setVersion(DataVersionUtil.updateVersion(process.getVersion()));
            return processesMapper.updateById(process) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
