package com.exam.civil.controller;

import com.exam.civil.mapper.ProvincialMapper;
import com.exam.civil.pojo.NationalJob;
import com.exam.civil.pojo.ProvincialJob;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/provincial")
public class ProvincialController {
    @Autowired
    private ProvincialMapper provincialMapper;

    @RequestMapping("/units")
    @ResponseBody
    public List<ProvincialJob> getUnits() {
        List<ProvincialJob> units = provincialMapper.selectUnits();
        return units;
    }

    @RequestMapping("/departments")
    @ResponseBody
    public List<ProvincialJob> getDepartments(ProvincialJob info) {
        List<ProvincialJob> departments = provincialMapper.selectDepartment(info.getUnit());
        return departments;
    }

    @RequestMapping("/jobs")
    @ResponseBody
    public List<ProvincialJob> getJobs(ProvincialJob info) {
        List<ProvincialJob> jobs = provincialMapper.selectJob(info);
        return jobs;
    }

    @RequestMapping("/follow")
    @ResponseBody
    public void followJob(Long id, Boolean isFollow) {
        provincialMapper.follow(id,isFollow);
    }

    @RequestMapping("/followList")
    @ResponseBody
    public List<ProvincialJob> followJob(ProvincialJob info) {
        System.out.println(info);
        List<ProvincialJob> jobs = provincialMapper.getFollow(info);
        return jobs;
    }


    @RequestMapping("/departments/count")
    @ResponseBody
    public List<ProvincialJob> getCountByDepartment(ProvincialJob info) {
        System.out.println(info);
        List<ProvincialJob> counts =  provincialMapper.selectCount(info);
        return counts;
    }

}
