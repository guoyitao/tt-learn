package com.example.yanjiushengtuijian.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.example.yanjiushengtuijian.domain.Data;
import com.example.yanjiushengtuijian.service.DataService;
import com.example.yanjiushengtuijian.service.TuijianService;
import com.example.yanjiushengtuijian.util.UserUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
public class DataController {

    @Resource
    private DataService dataService;

    @Resource
    private TuijianService tuijianService;

    //这个不用了，之前写错了
    @GetMapping("getAllQueryData")
    @ResponseBody//返回json
    public Object getAllQueryData(){
        return dataService.getAllQueryData();
    }
    
    /**
     *  查询所有省
     * @param 
     * @return java.lang.Object
     */
    @GetMapping("getShengs")
    @ResponseBody//返回json
    public Object getShengs(){
        return dataService.getShengs();
    }

    /**
     *  根据省 查询所有学校
     * @param
     * @return java.lang.Object
     */
    @GetMapping("getSchools")
    @ResponseBody//返回json
    public Object getSchools(@RequestParam String yuanXiaoShuDiSheng){
        return dataService.getSchools(yuanXiaoShuDiSheng);
    }

    /**
     *  根据学校  查询所有专业
     * @param
     * @return java.lang.Object
     */
    @GetMapping("getNameOfMajors")
    @ResponseBody//返回json
    public Object getNameOfMajors(@RequestParam String school){
        return dataService.getNameOfMajors(school);
    }

    /**
     *  根据专业  查询硕士类型
     * @param
     * @return java.lang.Object
     */
    @GetMapping("getTypesOfMaster")
    @ResponseBody//返回json
    public Object getTypesOfMaster(@RequestParam String nameOfMajor){
        return dataService.getTypesOfMaster(nameOfMajor);
    }

    /**
     *  查询学校和专业信息
     * @param page 页数
     * @param nameOfMajor 专业
     * @param school 学校
     * @param typesOfMaster  硕士类型
     * @param yuanXiaoShuDiSheng 省
     * @return java.lang.Object
     */
    @GetMapping("getDataList")
    @ResponseBody//返回json
    public Object getDataList(@RequestParam int page,
                             @RequestParam(required = false) String nameOfMajor,
                             @RequestParam(required = false) String school,
                              @RequestParam(required = false) String typesOfMaster,
                             @RequestParam(required = false) String yuanXiaoShuDiSheng
                             ){
        //查询对象  where后面的条件
        QueryWrapper<Data> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(school)) {
            wrapper.like("school",school);
        }
        if (StringUtils.isNotBlank(typesOfMaster)) {
            wrapper.eq("types_of_master",typesOfMaster);
        }
        if (StringUtils.isNotBlank(yuanXiaoShuDiSheng)) {
            wrapper.eq("yuan_xiao_shu_di_sheng",yuanXiaoShuDiSheng);
        }
        if (StringUtils.isNotBlank(nameOfMajor)) {
            wrapper.eq("name_of_major",nameOfMajor);
        }
        //分页查询
        Page<Data> dataPage = new Page<>(page , 10);
        IPage<Data> page1 = dataService.page(dataPage, wrapper);
        for (Data data : page1.getRecords()) {
            //政治分数，英语分数，数学分数，专业课分数
            buildJinFuShiDanKeJunFen(data);
        }
        return page1;
    }

    //导入学校数据
    @PostMapping("importOneData")
    public ModelAndView importData(Data data){
        ModelAndView modelAndView = new ModelAndView();
        if (UserUtil.getLoggedAdminImporter() != null) {
            buildJinFuShiDanKeJunFen(data);

            dataService.save(data);
            //还是返回导入的视图
            modelAndView.setViewName("importData");
            modelAndView.addObject("msg","保存成功");
        }else{
            //没登录就让用户登录
            modelAndView.setViewName("redirect:login.html");
            modelAndView.addObject("msg","请登录");
        }
        return modelAndView;
    }

    /**
     * 把单科平均分 拼装在一起
     * @param data
     * @return void
     */
    private void buildJinFuShiDanKeJunFen(Data data) {
        data.setJinFuShiDanKeQunFen(data.getAvgPolitics() + "," +
                data.getAvgEnglish() + "," +
                data.getAvgMath() + "," +
                data.getAvgProfessionalCourses());
    }

}
