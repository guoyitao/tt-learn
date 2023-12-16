package com.example.yanjiushengtuijian.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 推荐对象
 * @TableName t_tuijian
 */
@TableName(value ="t_tuijian")
public class Tuijian implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long tuijianId;

    /**
     * 
     */
    private String inputData;

    /**
     * 
     */
    private Long userId;

    /**
     * 学校和专业信息的id
     */
    private Long dataId;

    /**
     * 
     */
    private Date createDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getTuijianId() {
        return tuijianId;
    }

    /**
     * 
     */
    public void setTuijianId(Long tuijianId) {
        this.tuijianId = tuijianId;
    }

    /**
     * 
     */
    public String getInputData() {
        return inputData;
    }

    /**
     * 
     */
    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    /**
     * 
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 
     */
    public Long getDataId() {
        return dataId;
    }

    /**
     * 
     */
    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    /**
     * 
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}