package com.volkruss.toaru;

import com.volkruss.toaru.domain.model.user.User;
import com.volkruss.toaru.domain.repository.user.UserRepository;
import com.volkruss.toaru.domain.dto.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    // 追加
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/sample")
    @CrossOrigin
    public ResultObject post(@RequestBody SampleForm sampleForm){
        int id = sampleForm.getId();
        ResultObject resultObject = new ResultObject();
        resultObject.setName("zenn");
        resultObject.setLank(id);
        return resultObject;
    }

    // 追加
    @GetMapping("/test")
    public User get(){
        UserEntity userEntity = this.userRepository.findByName("zenn");
        return userEntity.toUser();
    }

    @GetMapping("/api/test")
    public String test(){
        return "認証が成功しています";
    }

}
