package chx.service;

import chx.dao.UserDao;
import chx.domain.User;

import java.util.List;

public class UserService {

    UserDao userDao = new UserDao();

    public List<User> findAll(){
       return userDao.findAll();
    }

    public List<User> find(String name){
        return userDao.find(name);
    }
}
