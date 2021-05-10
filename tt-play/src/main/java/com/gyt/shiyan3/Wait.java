package com.gyt.shiyan3;

import java.util.HashMap;
import java.util.Map;

public class Wait {
    private static final Map<Integer, Integer> queueMap = new HashMap<>();

    private String name;
    private Integer age;

    public Wait() {
    }

    public Wait(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    //    ①窗口根据年龄分配，不满 12 周岁在 1 号窗口，满 12 周岁不满 24 周岁在 2 号 窗口，满 24 周岁不满 36 周岁在 3 号窗口，以此类推；
//    ②序号为该窗口序号，如张三在 2 号窗口的第 15 位，即已经有 14 位满 12 周岁 不满 24 周岁的人在程序启动后已预约排号； ③若输入为你的学号，则程序终止； ④程序测试通过标准：不出现任何导致程序崩溃而无法正常运行的错误。
    public void show() {
        int window = (age / 12) + 1;
        Integer nums = queueMap.get(window);
        if (nums == null) {
            nums = 1;
            queueMap.put(window,nums);
        }else{
            queueMap.put(nums, ++nums);
        }
        System.out.printf("%s 窗口：%d 序号：%d \n",name,window,nums);
    }
}
