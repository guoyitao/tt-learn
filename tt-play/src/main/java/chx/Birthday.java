package chx;

public class Birthday {
    private int year;
    private int month;
    private int day;

    public Birthday() {
        this.year = 2000;
        this.month = 1;
        this.day = 1;
    }

    public Birthday(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void getBirthday(){
        System.out.printf("%d年%d月%d日\n",this.year,this.month,this.day);
    }
}
