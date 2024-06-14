package com.exam.civil.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.exam.civil.mapper.ProvincialMapper;
import com.exam.civil.pojo.ProvincialJob;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
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
    public PageInfo<ProvincialJob> getJobs(ProvincialJob info) {
        PageHelper.startPage(info.getPage(),info.getPageSize());
        List<ProvincialJob> jobs = provincialMapper.selectJob(info);
        PageInfo<ProvincialJob> pageInfo = new PageInfo<>(jobs);
        return pageInfo;
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

    @RequestMapping("/follow/export")
    @ResponseBody
    public void export(HttpServletResponse response, ProvincialJob info) {
        List<ProvincialJob> jobs = provincialMapper.getFollow(info);
        final String FILENAME = "关注信息";
        final String SHEETNAME = "关注列表";
        Class job = ProvincialJob.class;

        try {
            WriteCellStyle headerWriteCellStyle = new WriteCellStyle();
            headerWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headerWriteCellStyle, contentWriteCellStyle);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition","attachment;filename=" + FILENAME);
            EasyExcel.write(response.getOutputStream(),job).autoCloseStream(Boolean.FALSE).registerWriteHandler(horizontalCellStyleStrategy).sheet(SHEETNAME).doWrite(jobs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
