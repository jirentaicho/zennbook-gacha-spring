package com.volkruss.toaru.domain.repository.user;

import com.volkruss.toaru.domain.model.user.User;
import com.volkruss.toaru.domain.model.user.UserMapper;
import com.volkruss.toaru.domain.dto.user.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test_findByName(){
        UserEntity user = this.userRepository.findByName("zenn");
        assertEquals("zenn",user.getName());
    }

    @Test
    public void test_update(){
        UserEntity userEntity = this.userRepository.findByName("zenn");
        assertEquals(900, userEntity.getCoin());
        User user = userEntity.toUser();
        user.useCoin(300);

        // マッパーの利用
        UserEntity afterEntity = this.userMapper.toEntity(user);
        this.userRepository.updateCoin(afterEntity);

        UserEntity afterUserEntity = this.userRepository.findByName("zenn");
        assertEquals(600, afterUserEntity.getCoin());
    }
}