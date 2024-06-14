package com.exam.civil.pojo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
@ExcelIgnoreUnannotated
public class ProvincialJob {
    private Long id;
    private int sum;
    @ExcelProperty("招考人数")
    private int count;
    @ExcelProperty("招考单位")
    private String unit;
    @ExcelProperty("招考部门")
    private String department;
    @ExcelProperty("招考职位")
    private String name;
    @ExcelProperty("职位简介")
    private String introduction;
    @ExcelProperty("专业")
    private String major;
    @ExcelProperty("学历")
    private String qualification;
    @ExcelProperty("其它资格条件")
    private String otherterms;
    private String catagory;
    private String level;
    private String subjecttype;
    private String nature;
    private String number;
    private String psychologicaltest;
    private String physicalfitness;
    private Boolean followed;
    private int page;
    private int pageSize;
}
