package com.folder.app.service;

import com.folder.app.dto.ResultDTO;
import com.folder.app.dto.UserDto;

public interface UserService {
    public ResultDTO findAll();
    public ResultDTO editById(UserDto uDto);
    public ResultDTO delete(int no);
    public ResultDTO save(UserDto uDto);

}
