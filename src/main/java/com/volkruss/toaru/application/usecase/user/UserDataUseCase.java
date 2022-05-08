package com.volkruss.toaru.application.usecase.user;

import com.volkruss.toaru.domain.model.character.Character;

import java.util.List;

public interface UserDataUseCase {
    List<Character> getPossessionList();
}
