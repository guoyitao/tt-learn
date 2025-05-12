package yaxintool;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PackageToSqlUtil {
    public static void main(String[] args) {
        Date date = new Date();
        String dateNowStr = DateUtil.formatDateTime(date);

        String path = "C:\\Users\\guo\\Desktop\\亚信环境\\环境文档\\2025\\终端金币合约兑付清单IT支撑需求\\导入模板规则3.xlsx";
        String pathOut = "C:\\Users\\guo\\Desktop\\亚信环境\\环境文档\\2025\\终端金币合约兑付清单IT支撑需求\\导入营销包sql" + DateUtil.format(date, "yyyyMMddHHmmss") + ".sql";

        ExcelReader reader = ExcelUtil.getReader(new File(path));
        List<List<Object>> read = reader.read();

        boolean exist = FileUtil.exist(pathOut);
        if (!exist){
            FileUtil.touch(pathOut);
        }

        List<String> outStr = new ArrayList<>();
        for (int i = 1; i < read.size(); i++) {
            List<Object> line = read.get(i);
            String sql = getSql(dateNowStr,line.get(0).toString(),
                    line.get(1).toString(),
                    line.get(2).toString(),
                    line.get(3).toString(),
                    line.get(4).toString(),
                    line.get(5).toString());
            outStr.add(sql);
            System.out.println(sql);
        }
        FileUtil.writeLines(outStr,pathOut,"utf-8");
    }

    public static String getSql(String date, String code,String type,String name,String packageId,String all_yucun,String enable){
        return String.format("INSERT INTO UOP_RES.RES_TERM_5GD_COMMPARA (SUBSYS_CODE, PARAM_ATTR, PARAM_CODE, PARAM_NAME, PARA_CODE1, PARA_CODE2, PARA_CODE3, PARA_CODE4, PARA_CODE5, PARA_CODE6, PARA_CODE7, PARA_CODE8, PARA_CODE9, PARA_CODE10, PARA_CODE11, PARA_CODE12, PARA_CODE13, PARA_CODE14, PARA_CODE15, PARA_CODE16, PARA_CODE17, PARA_CODE18, PARA_CODE19, PARA_CODE20, PARA_CODE21, PARA_CODE22, PARA_CODE23, PARA_CODE24, PARA_TAG, PARA_CODE26, PARA_CODE27, PARA_CODE28, PARA_CODE29, PARA_CODE30, START_DATE, END_DATE, EPARCHY_CODE, UPDATE_STAFF_ID, UPDATE_DEPART_ID, UPDATE_TIME, REMARK, CREATE_TIME) VALUES ('CSM', 240, '%s', '%s', '%s', '%s', '%s', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '%s', null, null, null, null, null, TIMESTAMP '2024-07-01 00:00:33', DATE '2050-12-31', 'ZZZZ', null, null, TIMESTAMP '%s', null,TIMESTAMP '%s');", code, type,  packageId,name, all_yucun,enable,date,date);
    }
}
