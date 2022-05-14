package org.crystalca.backend.service.impl.pub;

import org.crystalca.backend.entity.ExchangesJson;
import org.crystalca.backend.entity.Processes;
import org.crystalca.backend.entity.pub.PubProcesses;
import org.crystalca.backend.mapper.pub.PubProcessesMapper;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.pub.CopyFromPublicDatabaseFilter;
import org.crystalca.backend.model.pub.PubProcessesGridFilter;
import org.crystalca.backend.service.IProcessesService;
import org.crystalca.backend.service.pub.IPubFlowsService;
import org.crystalca.backend.service.pub.IPubProcessesService;
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
public class PubProcessesServiceImpl extends ServiceImpl<PubProcessesMapper, PubProcesses> implements IPubProcessesService {
    @Autowired
    PubProcessesMapper pubProcessesMapper;

    public GridData<PubProcesses> getGrid(PubProcessesGridFilter filter) {
        QueryWrapper<PubProcesses> queryWrapper = new QueryWrapper<PubProcesses>();
        if (filter.getDataName() != null && !filter.getDataName().equals(""))
            queryWrapper.like(ColumnNameUtil.toDatabaseName("dataName"), filter.getDataName());
        if (filter.getSortBy() != null && !filter.getSortBy().equals("")) {
            if (filter.getOrderBy().equals("desc")) {
                queryWrapper.orderByDesc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            } else if (filter.getOrderBy().equals("asc")) {
                queryWrapper.orderByAsc(ColumnNameUtil.toDatabaseName(filter.getSortBy()));
            }
        }
        Page<PubProcesses> page = new Page<>(filter.getCurrent(), filter.getPageSize());
        Page<PubProcesses> resultPage = pubProcessesMapper.selectPage(page, queryWrapper);
        GridData<PubProcesses> gridData = new GridData<PubProcesses>(resultPage.getRecords(), resultPage.getTotal(), resultPage.getCurrent(), resultPage.getSize(), true);
        return gridData;
    }

    @Autowired
    private IProcessesService iProcessesService;
    @Autowired
    private IPubFlowsService iPubFlowsService;

    public String copy(CopyFromPublicDatabaseFilter filter) {
        try {
            PubProcesses pubProcess = pubProcessesMapper.selectById(filter.getPubId());
            Processes process = new Processes();
            process.copyPubData(pubProcess);
            process.setProjectId(filter.getProjectId());
            String exchanges = pubProcess.getExchanges();
            String exchangesJsonStr = "";
            if (exchanges != null && !(exchanges.trim().equals(""))) {
                JSONArray exchangeJsonArray = new JSONArray(exchanges);
                if (exchangeJsonArray.length() > 0) {
                    for (int i = 0; i < exchangeJsonArray.length(); i++) {
                        ExchangesJson exchangesJson = new ExchangesJson();
                        JSONObject exchangesJsonObject = exchangeJsonArray.getJSONObject(i);
                        JSONObject flow = exchangesJsonObject.getJSONObject("flow");
                        String flowId = flow.getString("@id");
                        exchangesJson.setFlowId(flowId);
                        exchangesJson.setInput(exchangesJsonObject.has("input") ? exchangesJsonObject.getBoolean("input") : null);
                        exchangesJson.setAmount(exchangesJsonObject.has("amount") ? exchangesJsonObject.getDouble("amount") : null);
                        exchangesJson.setInternalId(exchangesJsonObject.has("internalId") ? exchangesJsonObject.getInt("internalId") : null);
                        exchangesJson.setDescription(exchangesJsonObject.has("description") ? exchangesJsonObject.getString("description") : null);
                        exchangesJson.setAmountFormula(exchangesJsonObject.has("amountFormula") ? exchangesJsonObject.getString("amountFormula") : null);
                        exchangesJson.setAvoidedProduct(exchangesJsonObject.has("avoidedProduct") ? exchangesJsonObject.getBoolean("avoidedProduct") : null);
                        exchangesJson.setQuantitativeReference(exchangesJsonObject.has("quantitativeReference") ? exchangesJsonObject.getBoolean("quantitativeReference") : null);
                        exchangesJsonStr = exchangesJsonStr + exchangesJson.toJsonString();

                        CopyFromPublicDatabaseFilter filterFlow = new CopyFromPublicDatabaseFilter();
                        filterFlow.setProjectId(filter.getProjectId());
                        filterFlow.setPubId(flowId);
                        String copyFlow = iPubFlowsService.copy(filterFlow);
                        if (!(copyFlow.equals("ok")))
                            return copyFlow;
                    }
                }
            }
            if (exchangesJsonStr.equals(""))
                exchangesJsonStr = "[]";
            else
                exchangesJsonStr = "[" + exchangesJsonStr.substring(0, exchangesJsonStr.length() - 1) + "]";
            process.setExchanges(exchangesJsonStr);
            return iProcessesService.insert(process);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
