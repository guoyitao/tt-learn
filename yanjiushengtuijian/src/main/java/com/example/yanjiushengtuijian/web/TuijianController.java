package com.example.yanjiushengtuijian.web;

import com.example.yanjiushengtuijian.domain.Data;
import com.example.yanjiushengtuijian.domain.TuiJianParams;
import com.example.yanjiushengtuijian.domain.User;
import com.example.yanjiushengtuijian.service.DataService;
import com.example.yanjiushengtuijian.service.TuijianService;
import com.example.yanjiushengtuijian.util.UserUtil;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
public class TuijianController {

    static int zheDie = 5;

    @Resource
    private DataService dataService;

    @Resource
    private TuijianService tuijianService;

    /**
     *  详细推荐的表单页面
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("tuijianForm.html")
    public ModelAndView tuijianForm() {
        ModelAndView modelAndView = new ModelAndView();
        if (UserUtil.getLoggedUser() != null) {
            modelAndView.setViewName("tuijianForm");
            return modelAndView;
        }
        //没登录的就重定向到登录页面
        modelAndView.setViewName("redirect:login.html");
        return modelAndView;
    }

    /**
     *  系统推荐接口
     *  没登录会返回登录页面，成功推荐就把数据放到推荐结果页面
     * @param tuiJianParams
     * @return org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("systemTuijian")
    public ModelAndView systemTuijian(TuiJianParams tuiJianParams) {
        ModelAndView modelAndView = new ModelAndView();
        User user = UserUtil.getLoggedUser();
        if (user != null) {
            List<Data> data = tuijianService.systemTuijian(tuiJianParams, user);
            List<Data> data1 = new ArrayList<>();
            //分出需要折叠的数据
            if (data.size() > zheDie){

                int needZhe = data.size() - zheDie;
                for (int i = 0; i < needZhe; i++) {
                    data1.add(data.remove(0));
                }

            }
            //折叠的数据
            modelAndView.addObject("dataZhe", data1);
            //数据
            modelAndView.addObject("data", data);
            //标题
            modelAndView.addObject("title","系统推荐");
            modelAndView.setViewName("tuijianResult");
            return modelAndView;
        }
        //重定向到登录页面
        modelAndView.setViewName("redirect:login.html");
        return modelAndView;
    }

    /**
     *  详细推荐接口
     *  没登录会返回登录页面，成功推荐就把数据放到推荐结果页面
     * @param tuiJianParams
     * @return org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("detailTuijian")
    public ModelAndView detailTuijian(TuiJianParams tuiJianParams) {
        ModelAndView modelAndView = new ModelAndView();
        User user = UserUtil.getLoggedUser();
        if (user != null) {
            List<Data> data = tuijianService.detailTuijian(tuiJianParams, user);
            List<Data> data1 = new ArrayList<>();
            //分出需要折叠的数据
            if (data.size() > zheDie){
                int needZhe = data.size() - zheDie;
                for (int i = 0; i < needZhe; i++) {
                    data1.add(data.remove(0));
                }

            }
            //折叠的数据
            modelAndView.addObject("dataZhe", data1);
            //数据
            modelAndView.addObject("data", data);
            //标题
            modelAndView.addObject("title","详细学校推荐");
            //结果视图 对应tuijianResult.html
            modelAndView.setViewName("tuijianResult");
            return modelAndView;
        }
        //重定向到登录页面
        modelAndView.setViewName("redirect:login.html");
        return modelAndView;
    }

    /**
     *  获这个用户的取推荐的历史
     * @param start 时间起始
     * @param end 时间结束
     * @return org.springframework.web.servlet.ModelAndView
     */
    @GetMapping("getTuiJianHistory")
    public ModelAndView getTuiJianHistory(
            @RequestParam(required = false) String start,@RequestParam(required = false) String end
                                         ) throws ParseException {
        ModelAndView modelAndView = new ModelAndView();
        User user = UserUtil.getLoggedUser();
        //日期格式转换
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date startD = null;
        Date endD = null;
        if (user != null) {
            if (StringUtils.isNotBlank(start)){
                startD = format.parse(start);
            }
            if (StringUtils.isNotBlank(end)){
                endD = format.parse(end);
            }
            //查询数据
            modelAndView.addObject("data", tuijianService.getTuiJianHistory(user,startD,endD));
            //标题
            modelAndView.addObject("title","推荐历史");
            //返回推荐历史的视图
            modelAndView.setViewName("tuijianHistory");
            return modelAndView;
        }
        //没登录的用户会重定向到登录页面
        modelAndView.setViewName("redirect:login.html");
        return modelAndView;
    }

}
