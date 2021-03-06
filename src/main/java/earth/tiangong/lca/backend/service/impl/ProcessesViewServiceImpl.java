package earth.tiangong.lca.backend.service.impl;

import earth.tiangong.lca.backend.entity.ProcessesView;
import earth.tiangong.lca.backend.mapper.ProcessesViewMapper;
import earth.tiangong.lca.backend.service.IProcessesViewService;
import earth.tiangong.lca.backend.util.ColumnNameUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
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
 * @since 2022-03-13
 */
@Service
@DS("master")
public class ProcessesViewServiceImpl extends ServiceImpl<ProcessesViewMapper, ProcessesView> implements IProcessesViewService {
    @Autowired
    ProcessesViewMapper processesViewMapper;

    public ProcessesView getByDataId(Long projectId, String id) {
        QueryWrapper<ProcessesView> queryWrapper = new QueryWrapper<ProcessesView>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("project_id"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        return processesViewMapper.selectOne(queryWrapper);
    }

    public ProcessesView getByPkid(Long pkid) {
        return processesViewMapper.selectById(pkid);
    }
}
