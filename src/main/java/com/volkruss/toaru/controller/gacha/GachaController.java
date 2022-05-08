package com.volkruss.toaru.controller.gacha;

import com.volkruss.toaru.SampleForm;
import com.volkruss.toaru.application.usecase.gacha.GachaUseCase;
import com.volkruss.toaru.domain.model.character.Character;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GachaController {

    @Autowired
    private GachaUseCase gachaUseCase;

    @PostMapping("/api/playgacha")
    public List<CharacterOut> play(){
        List<Character> characters = this.gachaUseCase.getCharacters();
        List<CharacterOut> characterOuts = characters.stream()
                .map(CharacterMapper::toOut)
                .collect(Collectors.toList());
        return characterOuts;
    }

    /**
     * 返却オブジェクト
     */
    @AllArgsConstructor
    public static class CharacterOut{

        public String name;

        public static CharacterOut by(String name)
        {
            return new CharacterOut(name);
        }
    }

    /**
     * CharacterをCharacterOutに変換します。
     */
    public static class CharacterMapper{
        public static CharacterOut toOut(Character character){
            return CharacterOut.by(character.getName());
        }
    }
}
