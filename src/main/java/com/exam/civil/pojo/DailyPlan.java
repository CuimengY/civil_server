package com.exam.civil.pojo;

import lombok.Data;

@Data
public class DailyPlan {
    private int id;
    private String plan;
    private String date;
    private Boolean complete;
}
