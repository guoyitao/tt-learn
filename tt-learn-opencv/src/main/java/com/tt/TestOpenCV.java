package com.tt;


import org.opencv.core.*;
import org.opencv.features2d.FlannBasedMatcher;
import org.opencv.features2d.ORB;
import org.opencv.imgcodecs.Imgcodecs;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestOpenCV {
    static {
        URL url = ClassLoader.getSystemResource("lib/opencv_java440.dll");
        System.load(url.getPath());
    }
    public static void main(String[] args) {


//        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
//        Mat mat = Mat.eye(4,4, CvType.CV_8UC1);
//        System.out.println(mat.dump());
        Mat descriptors = extractORBFeatures("C:\\Users\\10042\\Pictures\\jdk2.png");
//        byte[] descriptorBytes = new byte[(int) (descriptors.total() * descriptors.elemSize())];
//        descriptors.get(0, 0, descriptorBytes);

        List<MatOfDMatch> matchingImages = findMatchingImages(descriptors, getDatabaseDescriptors(), 50);

        System.out.println(matchingImages);
    }

    // 序列化Mat对象到字节数组
    public static byte[] matToBytes(Mat mat) {
        int size = (int) (mat.total() * mat.elemSize());
        byte[] byteArray = new byte[size];
        mat.get(0, 0, byteArray); // 将Mat中的数据填充到byteArray中
        return byteArray;
    }

    // 将字节数组转换回Mat对象
    public static Mat bytesToMat(byte[] byteArray, int rows, int cols, int type) {
        Mat mat = new Mat(rows, cols, type);
        mat.put(0, 0, byteArray);
        return mat;
    }

    //特征计算
    public static Mat extractORBFeatures(String imagePath) {
        // 读取图像
        Mat image = Imgcodecs.imread(imagePath, Imgcodecs.IMREAD_GRAYSCALE);

        // 初始化 ORB 检测器
        ORB orb = ORB.create();

        // 检测关键点
        MatOfKeyPoint keyPoints = new MatOfKeyPoint();
        // 计算描述符
        Mat descriptors = new Mat();
        orb.detectAndCompute(image, new Mat(), keyPoints, descriptors);

        // 将描述符转换为CV_32F，以便FLANN使用
        Mat descriptors32F = new Mat();
        descriptors.convertTo(descriptors32F, CvType.CV_32F);
//        byte[] descriptorBytes = new byte[(int) (descriptors.total() * descriptors.elemSize())];
//        descriptors.get(0, 0, descriptorBytes);
        return descriptors32F;
    }

    public static List<Mat> getDatabaseDescriptors() {
        List<Mat> descriptorsList = new ArrayList<>();
        // 模拟从数据库获取特征描述符
        descriptorsList.add(TestOpenCV.extractORBFeatures("C:\\Users\\10042\\Pictures\\R.png"));
        descriptorsList.add(TestOpenCV.extractORBFeatures("C:\\Users\\10042\\Pictures\\jdk.png"));
        descriptorsList.add(TestOpenCV.extractORBFeatures("C:\\Users\\10042\\Pictures\\tomcat.png"));
        // 添加更多特征描述符...
        return descriptorsList;
    }

    public static List<MatOfDMatch> findMatchingImages(Mat queryDescriptors, List<Mat> databaseDescriptors, float distanceThreshold) {
        // 创建 FlannBasedMatcher
        FlannBasedMatcher flannMatcher = new FlannBasedMatcher();

        List<MatOfDMatch> matchesList = new ArrayList<>();

        // 对每个数据库中的特征描述符进行匹配
        for (Mat dbDescriptor : databaseDescriptors) {
            MatOfDMatch matches = new MatOfDMatch();
            flannMatcher.match(queryDescriptors, dbDescriptor, matches);

            // 过滤出符合距离阈值的匹配对
            List<DMatch> goodMatches = new ArrayList<>();
            for (DMatch match : matches.toArray()) {
                if (match.distance < distanceThreshold) {
                    goodMatches.add(match); // 只保留距离小于阈值的匹配对
                }
            }

            // 如果找到足够多的好匹配，说明图像匹配成功
            if (!goodMatches.isEmpty()) {
                MatOfDMatch goodMatchMat = new MatOfDMatch();
                goodMatchMat.fromList(goodMatches);
                matchesList.add(goodMatchMat);
            }
        }

        return matchesList;
    }




}
