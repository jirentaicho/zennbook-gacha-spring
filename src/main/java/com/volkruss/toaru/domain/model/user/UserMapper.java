package com.volkruss.toaru.domain.model.user;

import com.volkruss.toaru.domain.dto.user.UserEntity;

public interface UserMapper {
    UserEntity toEntity(User user);
}
