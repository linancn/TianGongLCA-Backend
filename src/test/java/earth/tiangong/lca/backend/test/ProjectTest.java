package earth.tiangong.lca.backend.test;

import earth.tiangong.lca.backend.entity.Projects;
import earth.tiangong.lca.backend.model.GridData;
import earth.tiangong.lca.backend.model.ProjectGridFilter;
import earth.tiangong.lca.backend.service.IProjectsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProjectTest {

    @Autowired
    IProjectsService iProjectsService;

    @Test
    public void getById() {
        iProjectsService.getById(9);
    }

    @Test
    public void getProjectPage() {
        ProjectGridFilter filter = new ProjectGridFilter();
        filter.setCurrent(1);
        filter.setPageSize(2);
        filter.setName("Pro");
        filter.setStar(false);
        filter.setSortBy("createTime");
        filter.setOrderBy("desc");
        GridData<Projects> projects = iProjectsService.getProjectGrid(filter);

        System.out.println("总记录数: " + projects.getTotal());
        projects.getData().forEach(System.out::println);
    }
}
