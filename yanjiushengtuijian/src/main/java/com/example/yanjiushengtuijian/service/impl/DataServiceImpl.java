package com.example.yanjiushengtuijian.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.yanjiushengtuijian.domain.Data;
import com.example.yanjiushengtuijian.service.DataService;
import com.example.yanjiushengtuijian.mapper.DataMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class DataServiceImpl extends ServiceImpl<DataMapper, Data>
    implements DataService{
    //不用了
    @Override
    public Map<String, Object> getAllQueryData() {
        QueryWrapper<Data> schoolTypeQuery = new QueryWrapper<>();
        schoolTypeQuery.select("DISTINCT  type_of_school");
        List<Data> schoolType = getBaseMapper().selectList(schoolTypeQuery);

        QueryWrapper<Data> shengQuery = new QueryWrapper<>();
        shengQuery.select("DISTINCT  yuan_xiao_shu_di_sheng");
        List<Data> sheng = getBaseMapper().selectList(shengQuery);


        QueryWrapper<Data> nameOfMajorQuery = new QueryWrapper<>();
        nameOfMajorQuery.select("DISTINCT  name_of_major");
        List<Data> nameOfMajorList = getBaseMapper().selectList(nameOfMajorQuery);

        //组装
        HashMap<String, Object> hashMap = new HashMap<>();
        List<String> typeOfSchools = schoolType.stream().map(Data::getTypeOfSchool).collect(Collectors.toList());
        hashMap.put("typeOfSchools", typeOfSchools);

        List<String> yuanXiaoShuDiShengs = sheng.stream().map(Data::getYuanXiaoShuDiSheng).collect(Collectors.toList());
        hashMap.put("yuanXiaoShuDiShengs", yuanXiaoShuDiShengs);

        List<String> nameOfMajors = nameOfMajorList.stream().map(Data::getNameOfMajor).collect(Collectors.toList());
        hashMap.put("nameOfMajors", nameOfMajors);

        return hashMap;
    }

    //获取所有省
    @Override
    public List<String> getShengs() {
        QueryWrapper<Data> shengQuery = new QueryWrapper<>();
        shengQuery.select("DISTINCT  yuan_xiao_shu_di_sheng");
        List<Data> sheng = getBaseMapper().selectList(shengQuery);
        return sheng.stream().map(Data::getYuanXiaoShuDiSheng).collect(Collectors.toList());
    }
    //根据省查询所有学校
    @Override
    public List<String> getSchools(String yuanXiaoShuDiSheng) {
        QueryWrapper<Data> schoolTypeQuery = new QueryWrapper<>();
        schoolTypeQuery.select("DISTINCT  school");
        schoolTypeQuery.eq("yuan_xiao_shu_di_sheng",yuanXiaoShuDiSheng);
        List<Data> schoolType = getBaseMapper().selectList(schoolTypeQuery);
        return schoolType.stream().map(Data::getSchool).collect(Collectors.toList());
    }
    //根据学校查询所有专业
    @Override
    public List<String> getNameOfMajors(String school) {
        QueryWrapper<Data> nameOfMajorQuery = new QueryWrapper<>();
        nameOfMajorQuery.select("DISTINCT  name_of_major");
        nameOfMajorQuery.eq("school",school);
        List<Data> nameOfMajorList = getBaseMapper().selectList(nameOfMajorQuery);
        return nameOfMajorList.stream().map(Data::getNameOfMajor).collect(Collectors.toList());
    }
    //根据专业名称 查询硕士类型
    @Override
    public List<String> getTypesOfMaster(String nameOfMajor) {
        QueryWrapper<Data> typeOfMasterQuery = new QueryWrapper<>();
        typeOfMasterQuery.select("DISTINCT  types_of_master");
        typeOfMasterQuery.eq("name_of_major",nameOfMajor);
        List<Data> typeOfMasterList = getBaseMapper().selectList(typeOfMasterQuery);
        return typeOfMasterList.stream().map(Data::getTypesOfMaster).collect(Collectors.toList());
    }

}




