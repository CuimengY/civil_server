package com.exam.civil.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CivilException extends RuntimeException {
    private String resultCode;
    private String message;
}
