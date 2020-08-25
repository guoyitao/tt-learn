package com.guo.demo;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests4读写分离 {



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
		User user = userMapper.selectById(1297805454344294402l);
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
