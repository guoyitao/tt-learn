package com.example.yanjiushengtuijian.mapper;

import com.example.yanjiushengtuijian.domain.Data;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yanjiushengtuijian.domain.TuiJianParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.example.yanjiushengtuijian.domain.Data
 */
public interface DataMapper extends BaseMapper<Data> {

    List<Data> systemTuijian(
            @Param("tuiJianParams") TuiJianParams tuiJianParams,
            @Param("maybeScore") Double maybeScore,
            @Param("maybeScore211") Double maybeScore211,
            @Param("maybeScoreShuangyiliu") Double maybeScoreShuangyiliu,
            @Param("maybeScoreFei") Double maybeScoreFei);

    List<Data> detailTuijian(@Param("tuiJianParams") TuiJianParams tuiJianParams);

}




