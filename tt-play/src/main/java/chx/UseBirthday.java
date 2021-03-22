package chx;

public class UseBirthday {
    public static void main(String[] args) {
        Birthday birthday = new Birthday();
        birthday.getBirthday();

        Birthday birthday1 = new Birthday(1234, 1, 4);
        birthday1.getBirthday();
    }
}
