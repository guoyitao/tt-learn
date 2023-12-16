package com.example.yanjiushengtuijian.util;

import com.example.yanjiushengtuijian.domain.Admin;
import com.example.yanjiushengtuijian.domain.User;
import com.example.yanjiushengtuijian.web.AdminController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//用户鉴权工具类
public class UserUtil {

    //检查是否登录
    public static User getLoggedUser(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }

    public static Admin getLoggedAdminManager(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if ( admin != null && AdminController.manager.equals(admin.getAdminType())){
            return admin;
        }
        return null;
    }

    public static Admin getLoggedAdminImporter(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null &&AdminController.importer.equals(admin.getAdminType())){
            return admin;
        }
        return null;
    }
}
