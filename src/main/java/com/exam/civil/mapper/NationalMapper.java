package com.exam.civil.mapper;

import com.exam.civil.pojo.NationalJob;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NationalMapper {
    List<NationalJob> selectDeparments();
    List<NationalJob> selectBureauByDepartment(long id);
    List<NationalJob> selectJobsByInfo(NationalJob info);
    int insertJob(NationalJob info);
    int deleteFollowjob(NationalJob info);
    List<NationalJob> selectFollowByInfo(NationalJob info);

    List<NationalJob> selectCount(NationalJob info);
}
