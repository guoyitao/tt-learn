package com.example.yanjiushengtuijian.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 管理员对象
 * @TableName t_admin
 */
@TableName(value ="t_admin")
public class Admin implements Serializable {
    /**
     * 
     */
    @TableId
    private Long adminId;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String password;

    /**
     * manager   importer
     */
    private String adminType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getAdminId() {
        return adminId;
    }

    /**
     * 
     */
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    /**
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * manager   importer
     */
    public String getAdminType() {
        return adminType;
    }

    /**
     * manager   importer
     */
    public void setAdminType(String adminType) {
        this.adminType = adminType;
    }
}