package earth.tiangong.lca.backend.model;

public class ProjectGridFilter extends BaseGridFilter {
    private String name;
    private Boolean star;

    public Boolean getStar() {
        return star;
    }

    public void setStar(Boolean star) {
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}