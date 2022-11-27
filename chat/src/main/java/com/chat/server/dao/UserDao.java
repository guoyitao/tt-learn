package com.chat.server.dao;

import com.chat.domain.User;
import com.chat.domain.UserUpdatePasswordRequestData;
import com.chat.util.UtilDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description://
 * @author: Luck_chen
 * @date: 2022/11/8 21:37
 * @Version 1.0.0.0
 */
public class UserDao {

    public User selectUser(String username){
        Connection conn = null;
        try {
            conn = UtilDB.getConn();
            PreparedStatement ps = conn.prepareStatement("select * from t_user where username = ?");
            ps.setString(1,username);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                String usernameDb = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                User user = new User(usernameDb,password,email);
                return user;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int insert(User loginUser) {
        Connection conn = null;
        try {
            conn = UtilDB.getConn();
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO t_user(username, password, email) VALUES (?, ?, ?)");
            ps.setString(1,loginUser.getUsername());
            ps.setString(2,loginUser.getPassword());
            ps.setString(3,loginUser.getEmail());
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public int delByName(String username) {
        Connection conn = null;
        try {
            conn = UtilDB.getConn();
            PreparedStatement ps = conn.prepareStatement("delete from t_user where username = ? ");
            ps.setString(1,username);
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int updatePassword(UserUpdatePasswordRequestData req) {
        Connection conn = null;
        try {
            conn = UtilDB.getConn();
            PreparedStatement ps = conn.prepareStatement("update t_user set password = ? where username = ? ");
            ps.setString(1,req.getNewPassword());
            ps.setString(2,req.getUsername());
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
