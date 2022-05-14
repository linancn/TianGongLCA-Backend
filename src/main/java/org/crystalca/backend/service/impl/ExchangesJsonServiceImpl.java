package org.crystalca.backend.service.impl;

import org.crystalca.backend.entity.ExchangesJson;
import org.crystalca.backend.mapper.ExchangesJsonMapper;
import org.crystalca.backend.service.IExchangesJsonService;
import org.crystalca.backend.util.ColumnNameUtil;

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
 * @author CrystaLCA
 * @since 2022-02-13
 */
@Service
public class ExchangesJsonServiceImpl extends ServiceImpl<ExchangesJsonMapper, ExchangesJson> implements IExchangesJsonService {
    @Autowired
    ExchangesJsonMapper exchangesJsonMapper;

    public List<ExchangesJson> getListByDataId(Long projectId, String processId) {
        QueryWrapper<ExchangesJson> queryWrapper = new QueryWrapper<ExchangesJson>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("processId"), processId);
        return exchangesJsonMapper.selectList(queryWrapper);
    }
}
