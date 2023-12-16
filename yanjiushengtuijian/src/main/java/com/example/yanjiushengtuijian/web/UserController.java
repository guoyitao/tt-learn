package com.example.yanjiushengtuijian.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.yanjiushengtuijian.domain.Data;
import com.example.yanjiushengtuijian.domain.User;
import com.example.yanjiushengtuijian.enity.Result;
import com.example.yanjiushengtuijian.mapper.UserMapper;
import com.example.yanjiushengtuijian.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Resource
    UserService userService;

    @Resource
    UserMapper userMapper;

    //普通用户登录
    @PostMapping("user/login")
    @ResponseBody   //返回json格式
    public Result loginUser(User user, HttpSession httpSession){
        Result result = new Result();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name",user.getUserName());
        userQueryWrapper.eq("password",user.getPassword());
        User userFromDB = userMapper.selectOne(userQueryWrapper);
        if (userFromDB != null) {
            //前端会判断这个code，代表是否登录成功
            result.setCode(0);
            httpSession.setAttribute("user",userFromDB);
            return result;
        }
        result.setCode(500);
        return result;
    }

    //普通用户注册
    @PostMapping("user/register")
    @ResponseBody  //返回json格式
    public Result register(User user, HttpSession httpSession){
        Result result = new Result();
        int insert = userMapper.insert(user);
        if (insert > 0){
            result.setCode(0);
            httpSession.setAttribute("user",user);
            return result;
        }
        result.setCode(500);
        return result;
    }

    //查询用户列表
    @GetMapping("user/list")
    @ResponseBody //返回json格式
    public List<User> getUsersList(@RequestParam int page){
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> dataPage = new Page<>(page , 10);
        return userService.page(dataPage).getRecords();
    }
}
