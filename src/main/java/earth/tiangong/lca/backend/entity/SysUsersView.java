package earth.tiangong.lca.backend.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sys_users_view")
public class SysUsersView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private Timestamp createTime;
    private String description;
    private Long createrId;
    private String roleName;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
  
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Long getCreaterId() {
        return createrId;
    }
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}