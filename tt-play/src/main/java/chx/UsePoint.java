package chx;

public class UsePoint {

    public static void main(String[] args) {
        Point point = new Point(1, 2);
        System.out.printf("%f %f\n",point.getX(),point.getY());

        Point point2 = new Point();
        point2.setX(123);
        System.out.printf("%f %f\n",point2.getX(),point2.getY());
    }
}
