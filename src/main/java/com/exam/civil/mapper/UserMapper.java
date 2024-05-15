package com.exam.civil.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    String findUser(String username);
}
