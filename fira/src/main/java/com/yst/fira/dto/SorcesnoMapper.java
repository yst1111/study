package com.yst.fira.dto;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yst.entity.dto.SorcesnoDto;
import com.yst.entity.pojo.Student;
import org.apache.ibatis.annotations.Param;

/**
* @author ly-yangst
* @description 针对表【sorcesNO】的数据库操作Mapper
* @createDate 2022-04-17 15:10:51
* @Entity com/yst/entity/pojo.Sorcesno
*/
public interface SorcesnoMapper extends BaseMapper<SorcesnoDto> {


    int updateit(@Param("student") Student student);

    int deleteSorces(@Param("params") Student student);

}
