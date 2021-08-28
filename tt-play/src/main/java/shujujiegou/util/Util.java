package shujujiegou.util;

import cn.hutool.core.io.file.FileReader;

public class Util {
    public static String readFileAs(String path){
        FileReader fileReader = new FileReader(path);
        return fileReader.readString();
    }

    public static boolean isEnglish(String charaString){

        return charaString.matches("^[a-zA-Z]*");

    }
}
