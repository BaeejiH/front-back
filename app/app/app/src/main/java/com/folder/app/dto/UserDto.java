package com.folder.app.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto {
    private int no;
    private String name;
    private String email;
    private String pwd;
    private String gender;
    private int del;    
    private LocalDateTime refDate; // Dto와 데아터를 전송하는 객체의 이름 오류시 오류번호 500(Internal Server Error), 실패 DB 컬럼명하고 단어 오류.
}
