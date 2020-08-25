package com.guo.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guo.demo.entity.Course;
import com.guo.demo.mapper.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests2水平分库 {


	@Autowired
	private CourseMapper courseMapper;

	@Test
	public void addCouse1() {
		for (int i = 0; i < 10; i++) {
			Course course = new Course();
			course.setCname("sql" + i + i);
			course.setCstatus("fabu");
			course.setUserId(110l + i);
			courseMapper.insert(course);
		}

	}

	@Test
	public void findCourse(){
		QueryWrapper<Course> wrapper = new QueryWrapper<>();
		wrapper.eq("cid",1297539353014218753l);
		wrapper.eq("user_id",111l);

		List<Course> courses = courseMapper.selectList(wrapper);
		System.out.println(courses);
	}
}
