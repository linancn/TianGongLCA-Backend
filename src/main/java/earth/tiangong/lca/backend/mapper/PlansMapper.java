package earth.tiangong.lca.backend.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import earth.tiangong.lca.backend.entity.Plans;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author TianGongLCA
 * @since 2022-01-17
 */
public interface PlansMapper extends BaseMapper<Plans> {
        @Select("select count(*) from public.plans where project_id = #{project_id,jdbcType=BIGINT} and exists( select 1 from jsonb_array_elements(model_cells->'nodes') as j(nodes) where (nodes#>>'{id}') = #{id,jdbcType=VARCHAR})")
        Long parentCountById(@Param("project_id") Long projectId, @Param("id") String id);
}
