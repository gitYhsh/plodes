package com.xlcxx.plodes.system.domian;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 310633547824784527L;
    /**
     * 用户ID
     */
    @Id
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户名
     */
    @Id
    private String username;

    /**用户密码**/
    @Column(name = "password")
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 部门ID
     */
    @Column(name = "dept_id")
    private Long deptId;

    @Transient
    private List<String> allAuthority;

    public List<String> getAllAuthority() {
        return allAuthority;
    }

    public void setAllAuthority(List<String> allAuthority) {
        this.allAuthority = allAuthority;
    }

    /**
     * 状态 1锁定 0有效
     */
    private String status;

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取部门ID
     *
     * @return dept_id - 部门ID
     */
    public Long getDeptId() {
        return deptId;
    }

    /**
     * 设置部门ID
     *
     * @param deptId 部门ID
     */
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取状态 1锁定 0有效
     *
     * @return status - 状态 1锁定 0有效
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态 1锁定 0有效
     *
     * @param status 状态 1锁定 0有效
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", deptId=" + deptId +
                ", status='" + status + '\'' +
                '}';
    }
}