package com.abc;

import java.io.*;

public class Work05 {
    public static void main(String[] args) {
        String inName = "E:\\aa.txt";
        String outName = "E:\\aa_copy.txt";
        File inFile = new File(inName);
        if (!inFile.exists()){
            System.out.println("输入文件不存在，请先创建" + inName);
        }
        File outFile = new File(outName);
        //存在就删除，不存在就创建
        if (outFile.exists()) {
            outFile.delete();
        }else{
            try {
                outFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        char[] chars = new char[5];
        BufferedReader br = null;
        BufferedWriter out = null;
        try {
            br = new BufferedReader(new FileReader(inFile));
            out = new BufferedWriter(new FileWriter(outFile));
            //一行一行读出来并写入
            while(br.read(chars) != -1){
                out.write(chars);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
