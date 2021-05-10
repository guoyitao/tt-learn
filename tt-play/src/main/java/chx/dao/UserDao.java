package chx.dao;

import chx.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    List<User> list = new ArrayList<>();

    public UserDao() {
        User user = new User(1,"aaa",2);
        User user1 = new User(2,"aaa",3);
        User user2 = new User(3,"bb",2);
        User user3= new User(4,"c",2);
        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);
    }

    public List<User> findAll(){
        return list;
    }

    public List<User> find(String name){
        List<User> t = new ArrayList<>();
        for (User user : list) {
            if (name.equals(user.getName())) {
                t.add(user);
            }
        }
        return t;
    }
}
