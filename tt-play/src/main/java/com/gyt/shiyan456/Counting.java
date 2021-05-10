package com.gyt.shiyan456;

public class Counting {
    private String name;
    private double grade;

    public Counting(String name) {
        this.name = name;
    }

    public Counting(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public void plus(double grade){
        this.grade += grade;
    }

    public void minus(double grade){
        this.grade -= grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Counting{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
