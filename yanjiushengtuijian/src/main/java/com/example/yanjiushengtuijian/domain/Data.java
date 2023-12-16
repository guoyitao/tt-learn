package com.example.yanjiushengtuijian.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 学校和专业信息对象
 * @TableName t_data
 */
@TableName(value ="t_data")
public class Data implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long dataId;

    /**
     * 
     */
    private String typeOfSchool;

    /**
     * 
     */
    private String school;

    /**
     * 
     */
    private String yuanXiaoShuDiSheng;

    /**
     * 
     */
    private String yuanXiaoShuDiShi;


    private String xueYuan;
    /**
     * 
     */
    private String nameOfMajor;

    /**
     * 
     */
    private String disciplinaryPower;

    /**
     * 
     */
    private String typesOfMaster;

    /**
     * 
     */
    private String politics;

    /**
     * 
     */
    private String math;

    /**
     * 
     */
    private String english;

    /**
     * 
     */
    private String professionalCourses;

    /**
     * 
     */
    private Double plan;

    /**
     * 
     */
    private Double baoKaoRenShu;

    /**
     * 
     */
    private Double actual;

    /**
     * 
     */
    private Double baoLuBi;

    /**
     * 
     */
    private Double fuShiRenShu;

    /**
     * 
     */
    private Double jinFuShiZongFenJunFen;

    /**
     * 
     */
    private String jinFuShiDanKeQunFen;

    /**
     * 
     */
    private Double niLuQuRenShu;

    /**
     * 
     */
    private Double niLuQuJunFen;

    private Double avgPolitics;

    private Double avgMath;

    private Double avgEnglish;

    private Double avgProfessionalCourses;

    /**
     * 
     */
    private String fuShiXiangGuan;

    /**
     * 
     */
    private String remark;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public String getXueYuan() {
        return xueYuan;
    }

    public void setXueYuan(String xueYuan) {
        this.xueYuan = xueYuan;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getTypeOfSchool() {
        return typeOfSchool;
    }

    public void setTypeOfSchool(String typeOfSchool) {
        this.typeOfSchool = typeOfSchool;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getYuanXiaoShuDiSheng() {
        return yuanXiaoShuDiSheng;
    }

    public void setYuanXiaoShuDiSheng(String yuanXiaoShuDiSheng) {
        this.yuanXiaoShuDiSheng = yuanXiaoShuDiSheng;
    }

    public String getYuanXiaoShuDiShi() {
        return yuanXiaoShuDiShi;
    }

    public void setYuanXiaoShuDiShi(String yuanXiaoShuDiShi) {
        this.yuanXiaoShuDiShi = yuanXiaoShuDiShi;
    }

    public String getNameOfMajor() {
        return nameOfMajor;
    }

    public void setNameOfMajor(String nameOfMajor) {
        this.nameOfMajor = nameOfMajor;
    }

    public String getDisciplinaryPower() {
        return disciplinaryPower;
    }

    public void setDisciplinaryPower(String disciplinaryPower) {
        this.disciplinaryPower = disciplinaryPower;
    }

    public String getTypesOfMaster() {
        return typesOfMaster;
    }

    public void setTypesOfMaster(String typesOfMaster) {
        this.typesOfMaster = typesOfMaster;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getProfessionalCourses() {
        return professionalCourses;
    }

    public void setProfessionalCourses(String professionalCourses) {
        this.professionalCourses = professionalCourses;
    }

    public Double getPlan() {
        return plan;
    }

    public void setPlan(Double plan) {
        this.plan = plan;
    }

    public Double getBaoKaoRenShu() {
        return baoKaoRenShu;
    }

    public void setBaoKaoRenShu(Double baoKaoRenShu) {
        this.baoKaoRenShu = baoKaoRenShu;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }

    public Double getBaoLuBi() {
        return baoLuBi;
    }

    public void setBaoLuBi(Double baoLuBi) {
        this.baoLuBi = baoLuBi;
    }

    public Double getFuShiRenShu() {
        return fuShiRenShu;
    }

    public void setFuShiRenShu(Double fuShiRenShu) {
        this.fuShiRenShu = fuShiRenShu;
    }

    public Double getJinFuShiZongFenJunFen() {
        return jinFuShiZongFenJunFen;
    }

    public void setJinFuShiZongFenJunFen(Double jinFuShiZongFenJunFen) {
        this.jinFuShiZongFenJunFen = jinFuShiZongFenJunFen;
    }

    public String getJinFuShiDanKeQunFen() {
        return jinFuShiDanKeQunFen;
    }

    public void setJinFuShiDanKeQunFen(String jinFuShiDanKeQunFen) {
        this.jinFuShiDanKeQunFen = jinFuShiDanKeQunFen;
    }

    public Double getNiLuQuRenShu() {
        return niLuQuRenShu;
    }

    public void setNiLuQuRenShu(Double niLuQuRenShu) {
        this.niLuQuRenShu = niLuQuRenShu;
    }

    public Double getNiLuQuJunFen() {
        return niLuQuJunFen;
    }

    public void setNiLuQuJunFen(Double niLuQuJunFen) {
        this.niLuQuJunFen = niLuQuJunFen;
    }

    public Double getAvgPolitics() {
        return avgPolitics;
    }

    public void setAvgPolitics(Double avgPolitics) {
        this.avgPolitics = avgPolitics;
    }

    public Double getAvgMath() {
        return avgMath;
    }

    public void setAvgMath(Double avgMath) {
        this.avgMath = avgMath;
    }

    public Double getAvgEnglish() {
        return avgEnglish;
    }

    public void setAvgEnglish(Double avgEnglish) {
        this.avgEnglish = avgEnglish;
    }

    public Double getAvgProfessionalCourses() {
        return avgProfessionalCourses;
    }

    public void setAvgProfessionalCourses(Double avgProfessionalCourses) {
        this.avgProfessionalCourses = avgProfessionalCourses;
    }

    public String getFuShiXiangGuan() {
        return fuShiXiangGuan;
    }

    public void setFuShiXiangGuan(String fuShiXiangGuan) {
        this.fuShiXiangGuan = fuShiXiangGuan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}