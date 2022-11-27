package com.chat.domain;

import java.util.Objects;

/**
 * @description://
 * @author: Luck_chen
 * @date: 2022/11/8 10:08
 * @Version 1.0.0.0
 */
public class User {
    private String username;
    private String password;
    private String email;


    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * 获取
     * @return Uname
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param Uname
     */
    public void setUsername(String Uname) {
        this.username = Uname;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "User{Uname = " + username + ", password = " + password + ", email = " + email + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return username.equals(user.username) && password.equals(user.password) && email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email);
    }
}
