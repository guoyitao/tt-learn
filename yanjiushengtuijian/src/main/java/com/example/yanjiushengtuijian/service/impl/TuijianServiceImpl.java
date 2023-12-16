package com.example.yanjiushengtuijian.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yanjiushengtuijian.domain.Data;
import com.example.yanjiushengtuijian.domain.TuiJianParams;
import com.example.yanjiushengtuijian.domain.Tuijian;
import com.example.yanjiushengtuijian.domain.User;
import com.example.yanjiushengtuijian.mapper.DataMapper;
import com.example.yanjiushengtuijian.service.TuijianService;
import com.example.yanjiushengtuijian.mapper.TuijianMapper;
import org.springframework.data.redis.connection.stream.Record;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class TuijianServiceImpl extends ServiceImpl<TuijianMapper, Tuijian>
        implements TuijianService {

    @Resource
    DataMapper dataMapper;

    @Resource
    TuijianMapper tuijianMapper;

    /**
     *  详细推荐
     * @param tuiJianParams
     * @param user
     * @return java.util.List<com.example.yanjiushengtuijian.domain.Data>
     */
    @Override
    public List<Data> detailTuijian(TuiJianParams tuiJianParams, User user) {
        Double jinFuShiZongFenJunFen = tuiJianParams.getJinFuShiZongFenJunFen();
        if (jinFuShiZongFenJunFen != null) {
            tuiJianParams.setJinFuShiZongFenJunFen0(jinFuShiZongFenJunFen - 40);
            tuiJianParams.setJinFuShiZongFenJunFen1(jinFuShiZongFenJunFen + 40);
        }
        Double baoLuBi = tuiJianParams.getBaoLuBi();
        if (baoLuBi != null && baoLuBi != 0) {
            tuiJianParams.setBaoLuBi(baoLuBi / 100);
        }

        List<Data> data = dataMapper.detailTuijian(tuiJianParams);
        saveHistory(tuiJianParams, user, data);
        return data;
    }

    /**
     *  获取推荐历史
     * @param user
     * @param start
     * @param end
     * @return java.util.List<com.example.yanjiushengtuijian.domain.Data>
     */
    @Override
    public List<Data> getTuiJianHistory(User user, Date start, Date end) {
        //查出这个时间段给用户推荐了哪些
        List<Tuijian> tuijians = tuijianMapper.selectListByDate(user, start, end);
        List<Long> dataIds = new ArrayList<>();
        //获取dataids
        for (Tuijian tuijian : tuijians) {
            dataIds.add(tuijian.getDataId());
        }
        if (dataIds.size() == 0) {
            return null;
        }
        //去学校专业信息表查询结果
        return dataMapper.selectBatchIds(dataIds);
    }

    /**
     * 系统推荐
     * @param tuiJianParams 用户传的参数
     * @param user 用户
     * @return
     */
    @Override
    public List<Data> systemTuijian(TuiJianParams tuiJianParams, User user) {
        Double englishScore = tuiJianParams.getEnglishScore() == null ? 0 : tuiJianParams.getEnglishScore();
        Double mathScore = tuiJianParams.getMathScore() == null ? 0 : tuiJianParams.getMathScore();
        Double professionalCoursesScore = tuiJianParams.getProfessionalCoursesScore() == null ? 0 : tuiJianParams.getProfessionalCoursesScore();
        Double politicsScore = tuiJianParams.getPoliticsScore() == null ? 0 : tuiJianParams.getPoliticsScore();
        //估分
        double maybeScore = englishScore + mathScore + professionalCoursesScore + politicsScore;

        //查询
        List<Data> data = dataMapper.systemTuijian(tuiJianParams, maybeScore, maybeScore - 10, maybeScore - 20, maybeScore - 30);
        //保存历史
        saveHistory(tuiJianParams, user, data);
        return data;
    }

    //拼装进复试单科平均分
    private void buildJinFuShiDanKeJunFen(Data data) {
        data.setJinFuShiDanKeQunFen(data.getAvgPolitics() + "," +
                data.getAvgEnglish() + "," +
                data.getAvgMath() + "," +
                data.getAvgProfessionalCourses());
    }

    /**
     *  保存推荐结果
     * @param tuiJianParams 用户传的推荐参数
     * @param user 用户
     * @param data 推荐结果
     * @return void
     */
    private void saveHistory(TuiJianParams tuiJianParams, User user, List<Data> data) {
        for (Data datum : data) {
            buildJinFuShiDanKeJunFen(datum);
            Tuijian tuijian = new Tuijian();
            tuijian.setUserId(user.getUserId());
            tuijian.setDataId(datum.getDataId());
            tuijian.setInputData(JSONUtil.toJsonStr(tuiJianParams));
            tuijian.setCreateDate(new Date());
            tuijianMapper.insert(tuijian);
        }
    }
}




