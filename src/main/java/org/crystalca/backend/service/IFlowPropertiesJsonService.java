package org.crystalca.backend.service;

import org.crystalca.backend.entity.FlowPropertiesJson;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CrystaLCA
 * @since 2022-02-17
 */
public interface IFlowPropertiesJsonService extends IService<FlowPropertiesJson> {
    List<FlowPropertiesJson> getListByDataId(Long projectId, String flowId);
}
