package chx;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Filasasd {

    public static void main(String[] args) {
        String filename = "";
        File file =  new File(filename);
        InputStreamReader inputSreamReader = null;
        try {
            inputSreamReader = new InputStreamReader(new FileInputStream(file));

            char[] chars = new char[1024];
            inputSreamReader.read(chars);
            System.out.println(new String(chars));

        }catch (Exception e){

        }finally {
            try {
                inputSreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
