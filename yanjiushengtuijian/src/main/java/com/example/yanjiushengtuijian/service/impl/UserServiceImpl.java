package com.example.yanjiushengtuijian.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yanjiushengtuijian.domain.User;
import com.example.yanjiushengtuijian.service.UserService;
import com.example.yanjiushengtuijian.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




