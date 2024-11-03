package com.folder.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.folder.app.dto.ResultDTO;
import com.folder.app.service.UserService;


@CrossOrigin(origins="http://localhost:8800")
@RestController
public class DataController {
    // git 오류 test
    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    

    @Autowired UserService uService;

   
    @GetMapping("/")
    public String home(){
        return "Data 준비중 ...";
    }

    @GetMapping("/api")
    public String api() {
        return "Api Data 준비중 ...";
    }

    @PostMapping("/findAll")
    public ResultDTO findAll(){
        return uService.findAll();
    }
    
    @PostMapping("/editById")
    public ResultDTO editById() {
        return null;
    }
    
    @DeleteMapping("/delete")
    public  ResultDTO delete(){
        return null;
    }

    @PutMapping("/save")
    public ResultDTO save() {
        return null;
    }
}
