package com.guo.stream;

import com.guo.DataClass;
import com.guo.bean.Person;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorMap1_9 extends DataClass {


    //list to map
    @Test
    public void test1(){
        List<Person> personList = Person.getPersonList(2);
        Map<String, Integer> collect = personList.stream().collect(Collectors.toMap(Person::getName, Person::getAge));
        System.out.println("Collectors.toMap(Person::getName, Person::getAge)" +collect);

//        Function.identity() 获得元素本身  即 List里面的Person元素
        Map<String, Person> collect1 = personList.stream().collect(Collectors.toMap(Person::getName, Function.identity()));

        System.out.println("Function.identity()" + collect1);
    }
}
