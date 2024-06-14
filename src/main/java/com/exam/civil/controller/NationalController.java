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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
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

    @RequestMapping("/follow/export")
    @ResponseBody
    public void exportFile(HttpServletResponse response, NationalJob info) throws IOException {
        List<NationalJob> jobs =  nationalMapper.selectFollowByInfo(info);
        final String FILENAME = "关注信息";
        final String SHEETNAME = "关注列表";
        Class job = NationalJob.class;
        //1.创建工作空间
        Workbook workbook = new XSSFWorkbook();
        //2.创建工作表
        Sheet sheet = workbook.createSheet(SHEETNAME);
        //2.1 创建标题行
        Row headerRow = sheet.createRow(0);
        //3.定义一个字体
        //3.1 创建字体
        Font headerFont = workbook.createFont();
        // 3.2 14号字体
        headerFont.setFontHeightInPoints((short) 14);
        //4. 声明样式CellStyle
        // 4.1 创建style
        CellStyle style = workbook.createCellStyle();
        // 4.2 将样式设置进style对象
        style.setFont(headerFont);
        // 4.3 水平和垂直居中
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        //设置列宽和表头样式
        String[] headers = {"部门名称","用人司局","招考职位","招考人数","职位简介","专业","学历","政治面貌","工作地点"};
        for (int i = 0; i < headers.length; i++) {
            sheet.setColumnWidth(i, 230 * 30);
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellStyle(style);
            headerCell.setCellValue(headers[i]);
        }
        for (int i = 0; i < jobs.size(); i++) {
            Row row = sheet.createRow(i + 1);
            for (int j = 0; j < headers.length; j++) {
                Cell cell = row.createCell(j);
                if(j == 0) {
                    cell.setCellValue(jobs.get(i).getDepartmentname());
                } else if (j == 1) {
                    cell.setCellValue(jobs.get(i).getBureau());
                } else if (j == 2) {
                    cell.setCellValue(jobs.get(i).getName());
                } else if (j == 3) {
                    cell.setCellValue(jobs.get(i).getCount());
                } else if (j == 4) {
                    cell.setCellValue(jobs.get(i).getIntroduction());
                } else if (j == 5) {
                    cell.setCellValue(jobs.get(i).getMajor());
                } else if (j == 6) {
                    cell.setCellValue(jobs.get(i).getQualification());
                } else if (j == 7) {
                    cell.setCellValue(jobs.get(i).getPolitical());
                }else if (j == 8) {
                    cell.setCellValue(jobs.get(i).getWorkposition());
                }
                cell.setCellStyle(style);
            }
        }

        try (OutputStream out = response.getOutputStream()) {
            response.setHeader("Content-Disposition","attachment; filename=" + FILENAME);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            workbook.write(out);
            out.flush();
            out.close();
            workbook.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
