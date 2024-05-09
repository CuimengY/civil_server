package com.exam.civil.mapper;

import com.exam.civil.pojo.NationalJob;
import com.exam.civil.pojo.ProvincialJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProvincialMapper {
    List<ProvincialJob> selectUnits();

    List<ProvincialJob> selectDepartment(String unit);

    List<ProvincialJob> selectJob(ProvincialJob info);

    void follow( @Param("id") Long id, @Param("isFollow") Boolean isFollow);

    List<ProvincialJob> getFollow(ProvincialJob info);

    List<ProvincialJob> selectCount(ProvincialJob info);
}
