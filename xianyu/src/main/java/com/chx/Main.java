package com.chx;

import java.util.ArrayList;

/**
 * @description://TODO
 * @author: Luck_chen
 * @date: 2023/4/9 16:14
 * @Version 1.0.0.0
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Long> ids = new ArrayList<>();
        long count = ids.stream().distinct().count();
        System.out.println(count);
    }
}
