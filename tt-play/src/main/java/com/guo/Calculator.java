package com.guo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public static void main(String[] args) throws IOException {
        Calculator calculator = new Calculator();
        String path = "C:\\mycode\\code\\tt-learn-master\\tt-play\\src\\main\\java\\com\\guo\\data.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(" ");
                int x = Integer.parseInt(split[0]);
                int y = Integer.parseInt(split[1]);
                System.out.println(calculator.substract(x,y));
            }
        }
    }

    public int substract(int x,int y){
        return x-y;
    }
}
