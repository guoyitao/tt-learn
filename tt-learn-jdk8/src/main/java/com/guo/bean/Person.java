package com.guo.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Person {

    private String name;

    private Integer age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static Person getPerson(){
        Person person = new Person();

        return person;
    }

    public static List<Person> getPersonList(int i){
        List<Person> list = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            Person person = getPerson();
            person.setName(person.getName()+j);
            person.setAge(person.getAge()+1);
            list.add(person);
        }
        return list;
    }
}
