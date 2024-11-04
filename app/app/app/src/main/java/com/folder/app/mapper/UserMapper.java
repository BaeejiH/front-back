package com.folder.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.folder.app.dto.UserDto;

@Mapper
public interface UserMapper {

    // 데이터 DB연결 성공
    @Select("select * from Example")
    public List<UserDto> findAll();

    @Update("UPDATE Example SET name = #{name}, pwd = #{pwd}, gender = #{gender} WHERE no = #{no}")
    public int editById(UserDto uDto);

    @Update("UPDATE Example SET del = 1 WHERE no = #{no}")
    public int delete(int no);

    // insert into Example (name,email,pwd,gender,del,refDate) VALUES ('가나다','가나다@gmail.com','111','M',0,'2023-01-01')
    @Insert("INSERT INTO Example (name, email, pwd, gender, del, refDate) VALUES (#{name}, #{email}, #{pwd}, #{gender}, #{del}, #{refDate})")
    public int save (UserDto uDTO);
}
