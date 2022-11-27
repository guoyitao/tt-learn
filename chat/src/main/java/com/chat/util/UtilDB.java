package com.chat.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @description:；连接数据库工具类
 * @author: Luck_chen
 * @date: 2022/11/8 10:59
 * @Version 1.0.0.0
 */
public class UtilDB {
    private static Properties props;

    static {
        try {
            InputStream in = UtilDB.class.getClassLoader().getResourceAsStream("db.properties");
            //通过类加载器找到classpath读取文件
            props = new Properties();
            props.load(in);
            in.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws Exception{
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        String driver = props.getProperty("driver");
        Class.forName(driver); //加载驱动
        return DriverManager.getConnection(url,username, password);
    }
}
