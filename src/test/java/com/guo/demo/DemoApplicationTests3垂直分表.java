package com.guo.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guo.demo.entity.Course;
import com.guo.demo.entity.Udict;
import com.guo.demo.entity.User;
import com.guo.demo.mapper.CourseMapper;
import com.guo.demo.mapper.UdictMapper;
import com.guo.demo.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests3垂直分表 {


	@Autowired
	private CourseMapper courseMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UdictMapper udictMapper;

	@Test
	public void addUser() {
		User user = new User();
		user.setUsername("小明");
		user.setUstatus("enable");

		userMapper.insert(user);
	}

	@Test
	public void findUser() {
		User user = userMapper.selectById(1297749989279150082l);
		System.out.println(user);
	}

	//	测试公共表
	@Test
	public void addUdict() {
		Udict udict = new Udict();
		udict.setUstatus("a");
		udict.setUvalues("已经启用");
		udictMapper.insert(udict);
	}

	@Test
	public void DeleteUdict() {
		udictMapper.deleteById(1297761713042608129l);
	}
}
