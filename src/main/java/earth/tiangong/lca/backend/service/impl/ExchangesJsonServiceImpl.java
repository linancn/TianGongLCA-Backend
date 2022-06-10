package earth.tiangong.lca.backend.service.impl;

import earth.tiangong.lca.backend.entity.ExchangesJson;
import earth.tiangong.lca.backend.mapper.ExchangesJsonMapper;
import earth.tiangong.lca.backend.service.IExchangesJsonService;
import earth.tiangong.lca.backend.util.ColumnNameUtil;

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
