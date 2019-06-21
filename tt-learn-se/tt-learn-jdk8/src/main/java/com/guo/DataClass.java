package com.guo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class DataClass {

    public static List<Integer> intList = new ArrayList<>();
    public static Map<String,Integer> stringIntegerMap = new HashMap<>();
    public static Set<String> stringSetset = new HashSet<>();
    public static List<String> stringList = new ArrayList<>();

    static {
        String[] strings = {"哦1撒，","撒哦。","哦aaaaaaaaaaaaaaaa撒，","撒aaaa哦。","哦撒，","撒as哦。","哦撒，","撒哦。","哦撒，","撒哦。","哦撒，","撒哦。","哦撒，","撒哦。","哦撒，","撒哦。","哦撒，","撒哦。","哦撒，","撒哦。",};

        for (int i = 0 ;i<strings.length;i++){
            stringList.add(strings[i]);
            intList.add(i);
            stringIntegerMap.put(strings[i],i);
        }
    }

    public long time;

    public DataClass()  {
    }

    public void startTime(){
       time =  System.nanoTime();
    }

    public void endTime(){
        System.out.println("一共用时:----------    "+ (System.nanoTime()-time)+"     -----------------");
    }

    public void print(Stream stream){
        stream.forEach(System.out::println);
    }

    public void print(Stream stream,String prefix){
        stream.forEach(
                s->System.out.println(prefix+s));
    }
}
