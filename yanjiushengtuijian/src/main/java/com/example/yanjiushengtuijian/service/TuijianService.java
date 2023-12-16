package com.example.yanjiushengtuijian.service;

import com.example.yanjiushengtuijian.domain.Data;
import com.example.yanjiushengtuijian.domain.TuiJianParams;
import com.example.yanjiushengtuijian.domain.Tuijian;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.yanjiushengtuijian.domain.User;

import java.util.Date;
import java.util.List;

/**
 *
 */
public interface TuijianService extends IService<Tuijian> {

    List<Data> detailTuijian(TuiJianParams tuiJianParams, User user);

    List<Data> getTuiJianHistory(User user, Date start, Date end);

    List<Data> systemTuijian(TuiJianParams tuiJianParams, User user);
}
