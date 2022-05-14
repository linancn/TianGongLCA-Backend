package org.crystalca.backend.test;

import org.crystalca.backend.entity.Projects;
import org.crystalca.backend.model.GridData;
import org.crystalca.backend.model.ProjectGridFilter;
import org.crystalca.backend.service.IProjectsService;
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
