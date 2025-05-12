package com.tt.pojo;

import org.opencv.core.Mat;

public class Image {
    private String path;
    private Mat oRBFeatures;

    public Image() {
    }

    public Image(String path) {
        this.path = path;
    }

    public Image(Mat oRBFeatures) {
        this.oRBFeatures = oRBFeatures;
    }

    public Image(String path, Mat oRBFeatures) {
        this.path = path;
        this.oRBFeatures = oRBFeatures;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Mat getoRBFeatures() {
        return oRBFeatures;
    }

    public void setoRBFeatures(Mat oRBFeatures) {
        this.oRBFeatures = oRBFeatures;
    }
}
