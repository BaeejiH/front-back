package com.folder.app.dao;

import java.util.List;

import com.folder.app.dto.UserDto;
//git test
//git test2
public interface UserDao {
    public List<UserDto>findAll();
    public int editById(UserDto uDto);
    public int save(UserDto uDto);
}
