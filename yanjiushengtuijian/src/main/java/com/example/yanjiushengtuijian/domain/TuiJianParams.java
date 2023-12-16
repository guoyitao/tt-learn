package com.example.yanjiushengtuijian.domain;

/**
 * 用户给服务器传参用户对象
 */
public class TuiJianParams extends Data {

    private String zhaoshengCompareType;

    private String shijiLuquCompareType;

    private Double  mathScore;

    private Double  englishScore;

    private Double  professionalCoursesScore;

    private Double politicsScore;

    private Double jinFuShiZongFenJunFen0;

    private Double jinFuShiZongFenJunFen1;

    private Double jinFuShiDanKeQunFen0;

    private Double jinFuShiDanKeQunFen1;

    public Double getPoliticsScore() {
        return politicsScore;
    }

    public void setPoliticsScore(Double politicsScore) {
        this.politicsScore = politicsScore;
    }

    public String getZhaoshengCompareType() {
        return zhaoshengCompareType;
    }

    public void setZhaoshengCompareType(String zhaoshengCompareType) {
        this.zhaoshengCompareType = zhaoshengCompareType;
    }

    public String getShijiLuquCompareType() {
        return shijiLuquCompareType;
    }

    public void setShijiLuquCompareType(String shijiLuquCompareType) {
        this.shijiLuquCompareType = shijiLuquCompareType;
    }

    public Double getMathScore() {
        return mathScore;
    }

    public void setMathScore(Double mathScore) {
        this.mathScore = mathScore;
    }

    public Double getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(Double englishScore) {
        this.englishScore = englishScore;
    }

    public Double getProfessionalCoursesScore() {
        return professionalCoursesScore;
    }

    public void setProfessionalCoursesScore(Double professionalCoursesScore) {
        this.professionalCoursesScore = professionalCoursesScore;
    }

    public Double getJinFuShiZongFenJunFen0() {
        return jinFuShiZongFenJunFen0;
    }

    public void setJinFuShiZongFenJunFen0(Double jinFuShiZongFenJunFen0) {
        this.jinFuShiZongFenJunFen0 = jinFuShiZongFenJunFen0;
    }

    public Double getJinFuShiZongFenJunFen1() {
        return jinFuShiZongFenJunFen1;
    }

    public void setJinFuShiZongFenJunFen1(Double jinFuShiZongFenJunFen1) {
        this.jinFuShiZongFenJunFen1 = jinFuShiZongFenJunFen1;
    }

    public Double getJinFuShiDanKeQunFen0() {
        return jinFuShiDanKeQunFen0;
    }

    public void setJinFuShiDanKeQunFen0(Double jinFuShiDanKeQunFen0) {
        this.jinFuShiDanKeQunFen0 = jinFuShiDanKeQunFen0;
    }

    public Double getJinFuShiDanKeQunFen1() {
        return jinFuShiDanKeQunFen1;
    }

    public void setJinFuShiDanKeQunFen1(Double jinFuShiDanKeQunFen1) {
        this.jinFuShiDanKeQunFen1 = jinFuShiDanKeQunFen1;
    }
}
