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
    @Column(name = "id")
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**用户密码**/
    @Column(name = "password")
    private String password;

    /**
     * 昵称
     */
    private String nickname;


    /**
     * 手机号
     * **/
    private String mobile;

    /**
     * qq 号
     * **/
    private String qq;

    /**
     * 微信号
     * **/
    private String wechat;

    /**
     * 创建时间
     * **/
    private String createtime;

    /**登陆时间**/
    private String lasttime;

    /**
     * 状态 1锁定 0有效
     */
    private String status;

    @Transient
    private List<String> allAuthority;


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public List<String> getAllAuthority() {
        return allAuthority;
    }

    public void setAllAuthority(List<String> allAuthority) {
        this.allAuthority = allAuthority;
    }

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
                ", status='" + status + '\'' +
                '}';
    }
}