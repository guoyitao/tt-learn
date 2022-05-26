package exceltest;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EXCEL，并设置级联下拉列表
 */
public class ExcelTest {
    /**
     * 影响最大行数
     */
    private static final int XLS_MAX_ROW = 60000;

    /**
     * 导出模板
     *
     * @param provinceList
     * @param areaFatherNameList
     * @param areaMap
     * @throws IOException
     */
    public static void exportHSSFTemplate(List<String> provinceList, List<String> areaFatherNameList, Map<String, List<String>> areaMap) {

        String[] tileList = new String[]{"姓名", "手机号", "省", "市", "县", "乡镇", "村"};

        //创建工作簿对象
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet");
        sheet.setDefaultColumnWidth(18);
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = getStyle(wb);
        HSSFCell cell = null;
        int provinceIndex = 0;
        for (int i = 0; i < tileList.length; i++) {
            String title = tileList[i];
            if ("省".equals(title)) {
                provinceIndex = i;
            }
            cell = row.createCell(i);
            cell.setCellValue(title);
            cell.setCellStyle(style);

        }
        //创建隐藏目录
        createHideSheetHSSF(wb, provinceList, areaFatherNameList, areaMap);

        //如果省市区的excel位置不是如上tileList中的位置，需要变更则需要INDIRECT中所在的列名称
        // 省规则
        DVConstraint provConstraint = DVConstraint.createExplicitListConstraint(provinceList.toArray(new String[]{}));
        CellRangeAddressList provRangeAddressList = new CellRangeAddressList(1, XLS_MAX_ROW, provinceIndex, provinceIndex);
        DataValidation provinceDataValidation = new HSSFDataValidation(provRangeAddressList, provConstraint);
        provinceDataValidation.createErrorBox("error", "请选择正确的省份");
        sheet.addValidationData(provinceDataValidation);


        //市规则
        CellRangeAddressList cityRange = new CellRangeAddressList(1, XLS_MAX_ROW, provinceIndex + 1, provinceIndex + 1);
        DataValidation cityValidation = new HSSFDataValidation(cityRange, DVConstraint.createFormulaListConstraint("INDIRECT($C1)"));
        cityValidation.createErrorBox("error", "请选择正确的市");
        sheet.addValidationData(cityValidation);

        //区县规则
        CellRangeAddressList areaRange = new CellRangeAddressList(1, XLS_MAX_ROW, provinceIndex + 2, provinceIndex + 2);
        DataValidation areaValidation = new HSSFDataValidation(areaRange, DVConstraint.createFormulaListConstraint("INDIRECT($D1)"));
        areaValidation.createErrorBox("error", "请选择正确的县");
        sheet.addValidationData(areaValidation);

        //区县乡镇
        CellRangeAddressList townRange = new CellRangeAddressList(1, XLS_MAX_ROW, provinceIndex + 3, provinceIndex + 3);
        DataValidation townValidation = new HSSFDataValidation(townRange, DVConstraint.createFormulaListConstraint("INDIRECT($E1)"));
        townValidation.createErrorBox("error", "请选择正确的乡镇");
        sheet.addValidationData(townValidation);

        //村规则
        CellRangeAddressList villageRange = new CellRangeAddressList(1, XLS_MAX_ROW, provinceIndex + 4, provinceIndex + 4);
        DataValidation villageValidation = new HSSFDataValidation(villageRange, DVConstraint.createFormulaListConstraint("INDIRECT($F1)"));
        villageValidation.createErrorBox("error", "请选择正确的村");
        sheet.addValidationData(villageValidation);

        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream("C:\\Users\\guo\\Desktop\\img\\test.xls");
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建隐藏页
     *
     * @param wb
     * @param provinceArr
     * @param areaFatherNameArr
     * @param areaMap
     */
    public static void createHideSheetHSSF(HSSFWorkbook wb, List<String> provinceArr, List<String> areaFatherNameArr, Map<String, List<String>> areaMap) {

        //创建一个专门用来存放地区信息的隐藏sheet页
        HSSFSheet hideSheet = wb.createSheet("area");

        int rowId = 0;
        // 设置第1行，存省的信息
        Row provinceRow = hideSheet.createRow(rowId++);
        for (int i = 0; i < provinceArr.size(); i++) {
            Cell provinceCell = provinceRow.createCell(i);
            provinceCell.setCellValue(provinceArr.get(i));
        }
        // 将具体的数据写入到每一行中，行开头为父级区域，后面是子区域。
        for (int i = 0; i < areaFatherNameArr.size(); i++) {
            String key = areaFatherNameArr.get(i);
            List<String> son = areaMap.get(key);
            Row prow = hideSheet.createRow(rowId++);
            prow.createCell(0).setCellValue(key);
            System.out.println(key);
            for (int j = 0; j < son.size(); j++) {
                Cell cell = prow.createCell(j + 1);
                cell.setCellValue(son.get(j));
            }
            // 添加名称管理器
            String range = getRange(1, rowId, son.size());
            Name name = wb.createName();
            //key不可重复
            name.setNameName(key);
            String formula = "area!" + range;
            name.setRefersToFormula(formula);
        }
        wb.setSheetHidden(1, true);

    }


    /**
     * 计算formula
     *
     * @param offset   偏移量，如果给0，表示从A列开始，1，就是从B列
     * @param rowId    第几行
     * @param colCount 一共多少列
     * @return 如果给入参 1,1,10. 表示从B1-K1。最终返回 $B$1:$K$1
     */
    public static String getRange(int offset, int rowId, int colCount) {
        char start = (char) ('A' + offset);
        if (colCount <= 25) {
            char end = (char) (start + colCount - 1);
            return "$" + start + "$" + rowId + ":$" + end + "$" + rowId;
        } else {
            char endPrefix = 'A';
            char endSuffix = 'A';
            if ((colCount - 25) / 26 == 0 || colCount == 51) {// 26-51之间，包括边界（仅两次字母表计算）
                if ((colCount - 25) % 26 == 0) {// 边界值
                    endSuffix = (char) ('A' + 25);
                } else {
                    endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
                }
            } else {// 51以上
                if ((colCount - 25) % 26 == 0) {
                    endSuffix = (char) ('A' + 25);
                    endPrefix = (char) (endPrefix + (colCount - 25) / 26 - 1);
                } else {
                    endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
                    endPrefix = (char) (endPrefix + (colCount - 25) / 26);
                }
            }
            return "$" + start + "$" + rowId + ":$" + endPrefix + endSuffix + "$" + rowId;
        }
    }


    /**
     * 样式
     *
     * @param wb
     * @return
     */
    private static HSSFCellStyle getStyle(HSSFWorkbook wb) {
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        //cellStyle.setWrapText(true);//自动换行

        HSSFFont font = wb.createFont();
        font.setFontName("宋体");//设置字体名称
        font.setFontHeightInPoints((short) 12);//字体大小
        font.setBold(true);
        cellStyle.setFont(font);
        return cellStyle;
    }


    public static void main(String[] args) {
        //省数据
        List<String> provinceArr = Arrays.asList("广东省", "河北省");
        //所有区域父类（只到最后一层的上级即可）
        List<String> areaFatherNameArr = Arrays.asList("广东省", "河北省", "深圳市", "衡水市", "宝安区", "武强县", "西乡街道办事处", "街关镇");
        //父子类关系
        Map<String, List<String>> areaMap = new HashMap<>();
        areaMap.put("广东省", Arrays.asList("深圳市"));
        areaMap.put("河北省", Arrays.asList("衡水市"));
        areaMap.put("深圳市", Arrays.asList("宝安区"));
        areaMap.put("衡水市", Arrays.asList("武强县"));
        areaMap.put("宝安区", Arrays.asList("西乡街道办事处"));
        areaMap.put("武强县", Arrays.asList("街关镇"));
        areaMap.put("西乡街道办事处", Arrays.asList("西乡社区居委会", "固戍社区居委会", "南昌社区居委会", "永丰社区居委会"));
        areaMap.put("街关镇", Arrays.asList("五里屯村委会", "北关村委会", "西关村委会", "南关村委会"));

        exportHSSFTemplate(provinceArr, areaFatherNameArr, areaMap);



    }

}
