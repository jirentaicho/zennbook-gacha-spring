package com.volkruss.toaru.application.usecase.gacha;

import com.volkruss.toaru.domain.model.character.Character;

import java.util.List;

public interface GachaUseCase {
    List<Character> getCharacters();
}
