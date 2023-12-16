package com.example.yanjiushengtuijian.service;

import com.example.yanjiushengtuijian.domain.Data;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface DataService extends IService<Data> {

    Map<String, Object> getAllQueryData();

    List<String> getShengs();

    List<String> getSchools(String yuanXiaoShuDiSheng);

    List<String>  getNameOfMajors(String school);

    List<String> getTypesOfMaster(String nameOfMajor);
}
