package com.test.springbootdocs.service;

import com.test.springbootdocs.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getList();
}
