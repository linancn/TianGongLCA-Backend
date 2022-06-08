package org.tiangonglca.backend.service;

import org.tiangonglca.backend.entity.ExchangesJson;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-02-17
 */
public interface IExchangesJsonService extends IService<ExchangesJson> {
    List<ExchangesJson> getListByDataId(Long projectId, String processId);
}
