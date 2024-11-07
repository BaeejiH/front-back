package com.folder.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.folder.app.dto.ResultDTO;
import com.folder.app.dto.UserDto;
import com.folder.app.service.MovieService;
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

    @Autowired MovieService mService;

   
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
    public ResultDTO editById(@RequestBody UserDto uDto) { // @RequestBody 데이터가 전달되는지 확인하기 위해, uDto 데이터 전달 정상적.
        System.out.println(uDto);
        return uService.editById(uDto);
    }
    
    @DeleteMapping("/delete")
    public  ResultDTO delete(@RequestParam("no") int no){
        return uService.delete(no);
    }

    @PutMapping("/save")
        public ResultDTO save(@RequestBody UserDto uDto) {
        return uService.save(uDto);
        }

    @GetMapping("/selectMovie")
    public ResultDTO selectMovie(){
        return mService.selectMovie();
    }    
}
