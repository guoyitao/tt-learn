package com.example.yanjiushengtuijian.mapper;

import com.example.yanjiushengtuijian.domain.Tuijian;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yanjiushengtuijian.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Entity com.example.yanjiushengtuijian.domain.Tuijian
 */
public interface TuijianMapper extends BaseMapper<Tuijian> {

    List<Tuijian> selectListByDate(@Param("user") User user, @Param("start") Date start, @Param("end") Date end);
}




