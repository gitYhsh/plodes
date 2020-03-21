package com.xlcxx.plodes.system.domian;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "t_role")
public class Role  implements Serializable {

    private static final long serialVersionUID = -1132975540063672837L;
    /**
     * 角色ID
     */
    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    /**
     * 角色名称
     */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 角色标识
     * **/
    @Column(name = "ROLE_ENAME")
    private String roleEname;


    /**
     * 获取角色ID
     *
     * @return ROLE_ID - 角色ID
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     *
     * @return ROLE_NAME - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleEname() {
        return roleEname;
    }

    public void setRoleEname(String roleEname) {
        this.roleEname = roleEname;
    }
}