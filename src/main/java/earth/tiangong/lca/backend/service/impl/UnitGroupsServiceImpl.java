package earth.tiangong.lca.backend.service.impl;

import earth.tiangong.lca.backend.entity.UnitGroups;
import earth.tiangong.lca.backend.entity.UnitsJson;
import earth.tiangong.lca.backend.mapper.UnitGroupsMapper;
import earth.tiangong.lca.backend.model.CopyFromOtherProjectsFilter;
import earth.tiangong.lca.backend.service.IUnitGroupsService;
import earth.tiangong.lca.backend.service.IUnitsJsonService;
import earth.tiangong.lca.backend.util.ColumnNameUtil;
import earth.tiangong.lca.backend.util.DataVersionUtil;

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
 * @since 2022-02-17
 */
@Service
@DS("master")
public class UnitGroupsServiceImpl extends ServiceImpl<UnitGroupsMapper, UnitGroups> implements IUnitGroupsService {
    @Autowired
    UnitGroupsMapper unitGroupsMapper;

    public UnitGroups getByDataId(Long projectId, String id) {
        QueryWrapper<UnitGroups> queryWrapper = new QueryWrapper<UnitGroups>();
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), projectId);
        queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), id);
        return unitGroupsMapper.selectOne(queryWrapper);
    }

    @Transactional
    public String insert(UnitGroups data) {
        try {
            if (data.getId() == null) {
                data.setId(UUID.randomUUID().toString());
                return unitGroupsMapper.insert(data) == 1 ? "ok" : "err";
            } else {
                QueryWrapper<UnitGroups> queryWrapper = new QueryWrapper<UnitGroups>();
                queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), data.getProjectId());
                queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), data.getId());
                if (unitGroupsMapper.selectCount(queryWrapper) == 0) {
                    return unitGroupsMapper.insert(data) == 1 ? "ok" : "err";
                } else {
                    return "ok";
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String create(UnitGroups data) {
        try {
            Date date = new Date();
            data.setLastChange(new Timestamp(date.getTime()));
            data.setVersion(DataVersionUtil.newVersion());
            return insert(data);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String copy(CopyFromOtherProjectsFilter filter) {
        try {
            QueryWrapper<UnitGroups> queryWrapper = new QueryWrapper<UnitGroups>();
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("projectId"), filter.getOthProjectId());
            queryWrapper.eq(ColumnNameUtil.toDatabaseName("id"), filter.getOthId());
            UnitGroups unitGroup = unitGroupsMapper.selectOne(queryWrapper);
            unitGroup.setProjectId(filter.getProjectId());
            return insert(unitGroup);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String updateBase(UnitGroups data) {
        try {
            UnitGroups unitGroups = unitGroupsMapper.selectById(data.getPkid());
            data.setCategoryId(unitGroups.getCategoryId());
            Date date = new Date();
            data.setLastChange(new Timestamp(date.getTime()));
            data.setVersion(DataVersionUtil.updateVersion(unitGroups.getVersion()));
            return unitGroupsMapper.updateById(data) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String updateCategory(UnitGroups data) {
        try {
            UnitGroups unitGroup = getByDataId(data.getProjectId(), data.getId());
            if (data.getCategoryId() == "")
                unitGroup.setCategoryId(null);
            else
                unitGroup.setCategoryId(data.getCategoryId());
            Date date = new Date();
            unitGroup.setLastChange(new Timestamp(date.getTime()));
            unitGroup.setVersion(DataVersionUtil.updateVersion(unitGroup.getVersion()));
            return unitGroupsMapper.updateById(unitGroup) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Autowired
    private IUnitsJsonService iUnitsJsonService;

    @Transactional
    public String updateUnitJson(UnitsJson data, String option) {
        try {
            UnitGroups unitGroup = getByDataId(data.getProjectId(), data.getId());
            String json = "";
            double cf = data.getConversionFactor();
            if (!option.equals("delete")) {
                if (option.equals("add")) {
                    data.setId(UUID.randomUUID().toString());
                    data.setVersion(DataVersionUtil.newVersion());
                } else {
                    data.setVersion(DataVersionUtil.updateVersion(data.getVersion()));
                }
                if (data.getReferenceUnit())
                    json = data.toJsonString(true, cf);
                else
                    json = data.toJsonString();
            }
            List<UnitsJson> list = iUnitsJsonService.getListByDataId(data.getProjectId(), data.getUnitGroupId());
            Iterator<UnitsJson> it = list.iterator();
            while (it.hasNext()) {
                UnitsJson next = it.next();
                if (!(next.getId().equals(data.getId()))) {
                    if (data.getReferenceUnit())
                        json = json + next.toJsonString(false, cf);
                    else
                        json = json + next.toJsonString();
                }
            }
            if (json.equals(""))
                json = "[]";
            else
                json = "[" + json.substring(0, json.length() - 1) + "]";
            unitGroup.setUnits(json);
            Date date = new Date();
            unitGroup.setLastChange(new Timestamp(date.getTime()));
            unitGroup.setVersion(DataVersionUtil.updateVersion(unitGroup.getVersion()));
            return unitGroupsMapper.updateById(unitGroup) == 1 ? "ok" : "err";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
