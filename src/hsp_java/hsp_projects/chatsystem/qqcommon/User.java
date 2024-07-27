package hsp_java.hsp_projects.chatsystem.qqcommon;

import java.io.Serializable;

//表示一个用户信息
public class User implements Serializable {

    private static final long serialVersionUID = 1L; //为了增强序列化的兼容性
    private String userId; //用户名
    private String passwd; //用户密码

    public User() {

    }

    public User(String userId, String passwd) {
        this.userId = userId;
        this.passwd = passwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
