package com.abc;

public class Manager {

    private String name;
    private Integer id;
    private Double salary;
    private Double bonus;

    public Manager() {
    }

    public Manager(String name, Integer id, Double salary, Double bonus) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.bonus = bonus;
    }

    public void work(){
        System.out.printf(
                "工号为%d，基本工资为%s，奖金为%s的项目经理正在努力的做着管理工作,分配任务,检查员工提交上来的代码.....\n"
                ,id,salary,bonus);
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

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }
}
