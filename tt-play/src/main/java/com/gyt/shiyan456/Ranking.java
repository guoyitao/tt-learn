package com.gyt.shiyan456;

public class Ranking extends  Counting{
    public Ranking(String name, double grade) {
        super(name, grade);
    }

    public void show(){
        double grade = super.getGrade();
        if (grade<=100 && grade>=90){
            System.out.println(this.toString() + "优");
        }else if (grade<=89 && grade>=80){
            System.out.println(this.toString() + "良");
        }else if (grade<=79 && grade>=70){
            System.out.println(this.toString() + "中");
        }else if (grade<=69 && grade>=60){
            System.out.println(this.toString() + "差");
        }else if (grade<=59 && grade>=0){
            System.out.println(this.toString() + "不及格");
        }else {
            System.out.println(this.toString() + "未知");
        }

    }
}
