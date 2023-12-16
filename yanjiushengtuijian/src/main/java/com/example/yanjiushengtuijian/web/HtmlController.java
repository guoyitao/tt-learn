package com.example.yanjiushengtuijian.web;

import com.example.yanjiushengtuijian.util.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {

    /**
     *  匹配
     *  http://127.0.0.1:8080/index.html
     *  http://127.0.0.1:8080/indexPage.html
     *  http://127.0.0.1:8080/
     *  http://127.0.0.1:8080
     *  的页面到主页
     *  普通用户没登录的话会返回登录页面
     * @param
     * @return java.lang.String
     */
    @RequestMapping({"index.html","/","indexPage.html"})
    public String login(){
        if (UserUtil.getLoggedUser() ==null) {
            return "login";
        }else{
            return "indexPage";
        }
    }

    /**
     *  直接返回登录页面
     *  不需要鉴权
     * @param
     * @return java.lang.String
     */
    @RequestMapping({"login.html"})
    public String loginPage(){
        return "login";
    }
    /**
     *  直接返回注册页面
     *  不需要鉴权
     * @param
     * @return java.lang.String
     */
    @RequestMapping("register.html")
    public String register(){
        return "register";
    }


}
