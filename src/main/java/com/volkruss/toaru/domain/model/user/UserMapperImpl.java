package com.volkruss.toaru.domain.model.user;

import com.volkruss.toaru.domain.dto.user.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setCoin(user.getCoin());
        return entity;
    }
}
