package com.chat.domain;

/**
 * @description://TODO
 * @author: Luck_chen
 * @date: 2022/11/14 20:30
 * @Version 1.0.0.0
 */
public class UserUpdatePasswordRequestData {
    private String username;
    private String oldPassword;
    private String newPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
