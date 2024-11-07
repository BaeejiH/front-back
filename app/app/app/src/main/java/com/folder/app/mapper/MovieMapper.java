package com.folder.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.folder.app.dto.MovieDTO;

@Mapper
public interface MovieMapper {

    // 영화 리스트
     @Select("select * from Movie")
    public List<MovieDTO> selectMovie();
}
