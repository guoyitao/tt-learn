package xianyu;

import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int k = scanner.nextInt();

        TreeSet<String[]> treeSet = new TreeSet<>(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int compare = o1[0].compareTo(o2[0]);
                if (compare == 0){
                    return o1[1].compareTo(o2[1]);
                }
                return compare;

            }
        });

        for (int i = 0; i < str.length(); i++) {

            if (i + k < str.length()+1){
                StringBuilder item = new StringBuilder();
                String[] itemI = new String[2];
                for (int j = i; j < i + k; j++) {

                    item.append(str.charAt(j));
                }
                itemI[0] = item.toString();
                itemI[1] = String.valueOf(i+1);
                treeSet.add(itemI);
                System.out.println("所有子串:"+item);
            }
        }

        for (String[] strings : treeSet) {
            System.out.println(strings[1]);
        }
    }
}
