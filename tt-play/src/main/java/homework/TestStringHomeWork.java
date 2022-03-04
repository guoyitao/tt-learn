package homework;

public class TestStringHomeWork {
    public static void main(String[] args) {
        String s1 = "Welcome to Java";
        String s2 = new String("Welcome to JAVA");
        String s3 = new String(" welcome to Java ");
        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));
        System.out.println(s1.equalsIgnoreCase(s3.trim()));
        System.out.println(s3.indexOf('o'));
        System.out.println(s3.lastIndexOf("o"));
        System.out.println(s1.substring(6, 12));
    }
}
