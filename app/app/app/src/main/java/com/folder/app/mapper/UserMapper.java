package com.folder.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.folder.app.dto.UserDto;

@Mapper
public interface UserMapper {

    // 데이터 DB연결 성공
    @Select("select * from example")
    public List<UserDto> findAll();

    @Update("UPDATE example SET name = #{name}, pwd = #{pwd}, gender = #{gender} WHERE no = #{no}")
    public int editById(UserDto uDto);

    @Delete("DELETE FROM example WHERE no = #{no}") // 삭제로직을 update가 아닌 delete로 변경해 실질적으로 사라지도록 구현. 0,1로 update하여 삭제처리 하려 했으나 복잡.
    public int delete(int no);

    // insert into Example (name,email,pwd,gender,del,refDate) VALUES ('가나다','가나다@gmail.com','111','M',0,'2023-01-01')
    @Insert("INSERT INTO example (name, email, pwd, gender, del, refDate) VALUES (#{name}, #{email}, #{pwd}, #{gender}, #{del}, #{refDate})")
    public int save (UserDto uDTO);
}
