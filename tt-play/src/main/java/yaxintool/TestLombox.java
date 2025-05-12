package yaxintool;

import com.fasterxml.jackson.core.JsonProcessingException;

public class TestLombox {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println(isValidDecimal("0"));       // true
        System.out.println(isValidDecimal("0.99"));    // true
        System.out.println(isValidDecimal("1.00"));    // true
        System.out.println(isValidDecimal(".5"));      // true
        System.out.println(isValidDecimal("1.01"));    // false（小数部分非零）
        System.out.println(isValidDecimal("0.123"));   // false（超过两位小数）
        System.out.println(isValidDecimal("-0.5"));    // false（负数）
        System.out.println(isValidDecimal("2.5"));     // false（超过1）
    }

    public static boolean isValidDecimal(String input) {
        if (input == null || input.isEmpty()) return false;

        // 允许：0, 0.1, 0.99, 1, 1.0, .5 (需补零)
        String regex = "^(0(\\.\\d{1,2})?|1(\\.0{1,2})?|\\.\\d{1,2})$";
        if (!input.matches(regex)) return false;

        // 转换 .5 为 0.5
        String normalized = input.replaceAll("^\\.", "0.");
        try {
            double value = Double.parseDouble(normalized);
            return value >= 0 && value <= 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
