package chx.ipeAK;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class IdTest {
    public static void main(String[] args) {
        List<String> strings = FileUtil.readLines(
                new File("C:\\Users\\guo\\Desktop\\Desktop\\id顺序\\t_course_item_user.txt"),
                Charset.defaultCharset());

        long[] ids = strings.stream().mapToLong(a -> {
            return Long.valueOf(a.replace("\"",""));
        }).toArray();

        List<Long> result = new ArrayList<>();
        for (int i = 0; i < ids.length-1; i++) {
            System.out.println(ids[i]);
            if (ids[i]> ids[i+1]){
                result.add(ids[i]);
            }
        }
        System.out.println("size: " + result.size());
        System.out.println(result);
    }
}
