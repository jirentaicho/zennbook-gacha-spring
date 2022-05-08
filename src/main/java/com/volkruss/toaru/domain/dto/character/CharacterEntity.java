package com.volkruss.toaru.domain.dto.character;

import com.volkruss.toaru.domain.model.character.Character;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CharacterEntity {
    private int id;
    private String name;
    private int lank;

    public Character toCharacter(){
        return new Character(id,name,lank);
    }
}
