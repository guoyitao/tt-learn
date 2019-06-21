package com.guo.bean;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.Assert.*;

public class PersonTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPerson() {
    }

    @Test
    public void getPersonList() {
    }

    @Test
    public void getName() {
        Thread thread = new Thread();
        ArrayList<Object> objects = new ArrayList<>();
        Iterator<Object> iterator = objects.iterator();
        while (iterator.hasNext()){
            iterator.next();
        }
    }
}