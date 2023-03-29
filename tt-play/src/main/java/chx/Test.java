package chx;

import java.util.HashMap;

public class Test {
    private String name  = "abc";
    public static void main(String[]args) {
        Test test = new Test();
        Test testB = new Test();
        String result = test.equals(testB) + ",";
        result += test.name.equals(testB.name) + ",";
        result += test.name == testB.name;
        System.out.println(result);
        HashMap map = new HashMap();
        map.put(null,null);
        int _$asd;
    }
}