package com.folder.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folder.app.dao.UserDao;
import com.folder.app.dto.ResultDTO;
import com.folder.app.dto.UserDto;

// servcie와 servcieImp
// service 인터페이스는 서비스가 제공할 기능을 정의
// serviceImp class는 service 인터페이스를 구현하여 실제 로직을 제공. (실제 필요한 로직이 담기는 곳) --> 유연성과 유지보수 때문


@Service
public class UserServiceImp implements UserService {

    private ResultDTO rDto;
    @Autowired UserDao uDao;

    @Override
    public ResultDTO findAll(){
        rDto = new ResultDTO();
        List<UserDto> resultList = uDao.findAll();
        System.out.println("Retrieved user list: " + resultList); // 결과 로깅
        if(resultList != null){
            rDto.setState(true);
            rDto.setResult(resultList);
        } else{
            rDto.setState(false);
            }
       
        return rDto;
    }
    @Override
    public ResultDTO editById(UserDto uDto){
        rDto = new ResultDTO();
        int state = uDao.editById((uDto));
        if(state == 1){
            rDto.setState(true);
            rDto.setMessage("사용자 수정이 성공하였습니다");
        } else {
            rDto.setState(false);
            rDto.setMessage("사용자 수정이 실패하였습니다");
        }
        return rDto;
    }
    @Override
    public ResultDTO delete(int no){
        rDto = new ResultDTO();
        int state = uDao.delete(no);
        if(state == 1){
            rDto.setState(true);
            rDto.setMessage("사용자 삭제가 성공하였습니다");
        }else {
            rDto.setState(false);
            rDto.setMessage("사용자 삭제가 실패하였습니다");
        }
        return rDto;
    }
    @Override
    public ResultDTO save(UserDto uDto){

        rDto = new ResultDTO();
        int state = uDao.save(uDto);
        System.out.println("Received UserDto: " + uDto);
        if(state == 1){
            rDto.setState(true);
            rDto.setMessage("사용자 생성이 성공하였습니다");
        }else {
            rDto.setState(false);
            rDto.setMessage("사용자 생성이 실패하였습니다");
        }
        return rDto;
    }

}
