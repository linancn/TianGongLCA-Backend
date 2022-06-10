package earth.tiangong.lca.backend.service;

// import org.checkerframework.common.value.qual.BoolVal;
import earth.tiangong.lca.backend.entity.ExchangesJsonView;
import earth.tiangong.lca.backend.model.ExchangesJsonViewGridFilter;
import earth.tiangong.lca.backend.model.GridData;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-17
 */
public interface IExchangesJsonViewService extends IService<ExchangesJsonView> {
    GridData<ExchangesJsonView> getGrid(ExchangesJsonViewGridFilter filter);

    ExchangesJsonView getByDataId(Long projectId, String processId, Long internalId, Boolean input);
}
