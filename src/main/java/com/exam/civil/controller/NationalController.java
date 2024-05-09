/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.exam.civil.controller;

import com.exam.civil.mapper.NationalMapper;
import com.exam.civil.pojo.NationalJob;
import com.exam.civil.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
@CrossOrigin
@RequestMapping("/national")
public class NationalController {

    @Autowired
    private NationalMapper nationalMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    // http://127.0.0.1:8080/hello?name=lisi


    @RequestMapping("/allDepartments")
    @ResponseBody
    public Result<List<NationalJob>> getDepartments() {
        List<NationalJob> departments = nationalMapper.selectDeparments();
        return Result.success(departments);
    }
    @RequestMapping("/bureau")
    @ResponseBody
    public Result<List<NationalJob>> getBureaus(NationalJob info) {
        List<NationalJob> bureaus = nationalMapper.selectBureauByDepartment(info.getDepartmentid());
        return Result.success(bureaus);
    }

    @RequestMapping("/jobs")
    @ResponseBody
    public Result<List<NationalJob>> getNationalJobs(NationalJob info) {
        System.out.println(info);
        List<NationalJob> jobs =  nationalMapper.selectJobsByInfo(info);
//        redisTemplate.opsForValue().set("name","小明");
//        String city = (String) redisTemplate.opsForValue().get("name"); // 拿到key为name的值
//        System.out.println(city);
//        redisTemplate.opsForValue().set("code","1234",3, TimeUnit.MINUTES); // 设置过期时间为三分钟
//        redisTemplate.opsForValue().setIfAbsent("lock","1"); // 设置lock为k的唯一值
//        redisTemplate.opsForValue().setIfAbsent("lock","2");
        return Result.success(jobs);
    }

    @RequestMapping("/job/updatefollow")
    @ResponseBody
    public Result followJob(NationalJob info) {
        System.out.println(info);
        int num = nationalMapper.insertJob(info);
        if(num != 0) {
            return Result.success();
        }else {
            return Result.failed("关注失败");
        }
    }

    @RequestMapping("/job/deletefollow")
    @ResponseBody
    public void deleteFollowJob(NationalJob info) {
        nationalMapper.deleteFollowjob(info);
    }


    @RequestMapping("/job/follow")
    @ResponseBody
    public Result<List<NationalJob>> getFollowByInfo(NationalJob info) {
        System.out.println(info);
        List<NationalJob> jobs =  nationalMapper.selectFollowByInfo(info);
        return Result.success(jobs);
    }

    @RequestMapping("/bureau/count")
    @ResponseBody
    public Result<List<NationalJob>> getCountByDepartment(NationalJob info) {
        System.out.println(info);
        List<NationalJob> counts =  nationalMapper.selectCount(info);
        return Result.success(counts);
    }

}
