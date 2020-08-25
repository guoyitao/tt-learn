package com.guo.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guo.demo.entity.Course;
import com.guo.demo.mapper.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests1水平分表 {


	@Autowired
	private CourseMapper courseMapper;

	@Test
	public void addCouse() {

		for (int i = 0; i < 10; i++) {
			Course course = new Course();
			course.setCname("mysql" + i);
			course.setCstatus("fabu");
			course.setUserId(100l);

			courseMapper.insert(course);
		}
	}

	@Test
	public void findCourse(){
		QueryWrapper<Course> wrapper = new QueryWrapper<>();
		wrapper.eq("cid",504363495605665793l);

		Course course = courseMapper.selectOne(wrapper);
		System.out.println(course);

		Course course1 = courseMapper.selectById(504363495928627200l);
		System.out.println(course1);

	}

}
