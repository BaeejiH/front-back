package com.folder.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.folder.app.dto.UserDto;
import com.folder.app.mapper.UserMapper;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired UserMapper userMapper;

    @Override
    public List<UserDto>findAll(){
        return userMapper.findAll();
    }

    @Override
    public int editById(UserDto uDto){
        return userMapper.editById(uDto);
    }

    @Override
    public int save(UserDto uDto){
        return userMapper.save(uDto);
    }

    @Override
    public int delete(int no){
        return userMapper.delete(no);
    }
}
