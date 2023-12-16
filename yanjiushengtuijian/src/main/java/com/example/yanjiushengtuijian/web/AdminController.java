package com.example.yanjiushengtuijian.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.yanjiushengtuijian.domain.Admin;
import com.example.yanjiushengtuijian.domain.User;
import com.example.yanjiushengtuijian.enity.Result;
import com.example.yanjiushengtuijian.mapper.AdminMapper;
import com.example.yanjiushengtuijian.service.AdminService;
import com.example.yanjiushengtuijian.util.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    //管理员类型的常量
    public static final String manager = "manager";
    public static final String importer = "importer";
    @Resource
    private AdminMapper adminMapper;


    /**
     * 获取管理员页面
     * 没登录的会重定向到登录页面
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("manageUser.html")
    public ModelAndView manageUser(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        //判断登录的用户是不是管留言
        if (UserUtil.getLoggedAdminManager() != null) {
            modelAndView.setViewName("manageUser");
            return modelAndView;
        }else{
            //没有权限就重定向到登录页面
            modelAndView.setViewName("redirect:login.html");
            return modelAndView;
        }
    }

    /**
     *  导入学校信息数据的页面
     *  没登录的会重定向到登录页面
     * @param request
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("importData.html")
    public ModelAndView importData(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        if (UserUtil.getLoggedAdminImporter() != null) {
            modelAndView.setViewName("importData");
            return modelAndView;
        }else{
            modelAndView.setViewName("redirect:login.html");
            return modelAndView;
        }
    }

    /**
     * 管理员登录
     * @param userName 账号名
     * @param password 密码
     * @param httpSession Servlet里面的会话
     * @return
     */
    @RequestMapping("admin/login")
    @ResponseBody
    public Result adminLogin(
            @RequestParam String userName,
            @RequestParam String password, HttpSession httpSession){
        //根据用户名和密码查询用户
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",userName);
        queryWrapper.eq("password",password);
        Admin one = adminMapper.selectOne(queryWrapper);
        Result result = new Result();
        if (one != null){
            httpSession.setAttribute("admin",one);
            //根据管理员类型返回需要跳转的页面给前端，前端拿到data会跳转
            if (manager.equals(one.getAdminType())){
                result.setCode(302);
                result.setData("/manageUser.html");
            }else if (importer.equals(one.getAdminType())){
                result.setCode(302);
                result.setData("/importData.html");
            }
            return result;
        }
        result.setCode(0);
        return result;
    }
}
