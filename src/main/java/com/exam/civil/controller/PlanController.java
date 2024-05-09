package com.exam.civil.controller;

import com.exam.civil.mapper.PlanMapper;
import com.exam.civil.pojo.DailyPlan;
import com.exam.civil.pojo.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanMapper planMapper;

    @ResponseBody
    @RequestMapping(value = "/getPlan", method = RequestMethod.GET)
    public List<Plan> getPlan(String part) {
        List<Plan> plans = planMapper.selectPlan(part);
        System.out.println(plans);
        return plans;
    }

    @ResponseBody
    @RequestMapping(value = "/updatePlan", method = RequestMethod.POST)
    public void updatePlan(Plan plan) {
        planMapper.updatePlan(plan);
    }

    @ResponseBody
    @RequestMapping(value = "/deletePlan" , method = RequestMethod.DELETE)
    public void deletePlan(int id) {
        planMapper.deletePlan(id);
    }

    @ResponseBody
    @RequestMapping(value = "/addPlan", method = RequestMethod.POST)
    public void addPlan(Plan plan) {
        planMapper.addPlan(plan);
    }

    @ResponseBody
    @RequestMapping(value = "/getDailyPlan", method = RequestMethod.GET)
    public List<DailyPlan> getDailyPlan() {
        List<DailyPlan> plans = planMapper.selectDailyPlan();
        return  plans;
    }

    @ResponseBody
    @RequestMapping(value = "/getDailyPlanByDate", method = RequestMethod.GET)
    public List<DailyPlan> getDailyPlanByDate(String date) {
        List<DailyPlan> plans = planMapper.selectDailyPlanByDate(date);
        return  plans;
    }

    @ResponseBody
    @RequestMapping(value = "/updateDailyPlan", method = RequestMethod.POST)
    public void updateDailyPlan(DailyPlan plan) {
        planMapper.updateDailyPlan(plan);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDailyPlan" , method = RequestMethod.DELETE)
    public void deleteDailyPlan(int id) {
        planMapper.deleteDailyPlan(id);
    }
    @ResponseBody
    @RequestMapping(value = "/addDailyPlan", method = RequestMethod.POST)
    public void addDailyPlan(DailyPlan plan) {
        planMapper.addDailyPlan(plan);
    }

}
