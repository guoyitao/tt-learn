package yaxintool;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CityToSqlUtil {
    public static void main(String[] args) {
        Date date = new Date();

        String path = "C:\\Users\\guo\\Desktop\\工作簿3.xlsx";
        String pathOut = "C:\\Users\\guo\\Desktop\\亚信环境\\环境文档\\2025\\集团手机宝和会员数据缺省\\导入区县sql" + DateUtil.format(date, "yyyyMMddHHmmss") + ".sql";

        ExcelReader reader = ExcelUtil.getReader(new File(path));
        List<List<Object>> read = reader.read();

        boolean exist = FileUtil.exist(pathOut);
        if (!exist){
            FileUtil.touch(pathOut);
        }

        List<String> outStr = new ArrayList<>();
        for (int i = 1; i < read.size(); i++) {
            List<Object> line = read.get(i);
            String sql = getSql(line.get(0).toString(),
                    line.get(1).toString(),
                    line.get(2).toString(),
                    line.get(3).toString(),line.get(4).toString());
            outStr.add(sql);
            System.out.println(sql);
        }
        FileUtil.writeLines(outStr,pathOut,"utf-8");
    }

    public static String getSql(String ...date){
        return String.format("INSERT INTO UCR_CEN1.TD_M_AREA_TM_MEMBER (AREA_ID, AREA_NAME, LEL, PARENT_AREA_ID) VALUES ('%s', '%s', %s, '%s');", date[2], date[3],  date[4],date[0]);
    }
}
