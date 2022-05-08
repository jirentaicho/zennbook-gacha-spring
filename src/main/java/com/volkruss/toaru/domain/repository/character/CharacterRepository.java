package com.volkruss.toaru.domain.repository.character;

import com.volkruss.toaru.domain.dto.character.CharacterEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CharacterRepository {
    List<CharacterEntity> getCharacters();
}
