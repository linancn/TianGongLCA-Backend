package earth.tiangong.lca.backend.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sys_user_roles")
public class SysUserRoles implements Serializable {
    private Long userId;

    private Long roleId;

    private Long createrId;

    private Timestamp createTime;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}