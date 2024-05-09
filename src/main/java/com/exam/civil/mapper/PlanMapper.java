package com.exam.civil.mapper;

import com.exam.civil.pojo.DailyPlan;
import com.exam.civil.pojo.Plan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanMapper {
    List<Plan> selectPlan(String part);

    void updatePlan(Plan plan);

    void deletePlan(int id);

    void addPlan(Plan plan);

    List<DailyPlan> selectDailyPlan();

    void updateDailyPlan(DailyPlan plan);

    void deleteDailyPlan(int id);

    void addDailyPlan(DailyPlan plan);

    List<DailyPlan> selectDailyPlanByDate(String date);
}
