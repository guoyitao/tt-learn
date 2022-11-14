package com.abc;

public class Coder {
    private String name;
    private Integer id;
    private Double salary;

    public void work(){
        System.out.printf(
                "工号为%s，基本工资为%s的程序员正在努力的写着代码......\n"
                ,id,salary);
    }

    public Coder(String name, Integer id, Double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public Coder() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

}
