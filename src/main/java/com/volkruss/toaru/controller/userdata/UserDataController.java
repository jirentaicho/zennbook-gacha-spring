package com.volkruss.toaru.controller.userdata;

import com.volkruss.toaru.application.usecase.user.UserDataUseCase;
import com.volkruss.toaru.controller.gacha.GachaController;
import com.volkruss.toaru.domain.model.character.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserDataController {

    @Autowired
    private UserDataUseCase userDataUseCase;

    @GetMapping("api/stock")
    public List<GachaController.CharacterOut> mylist(){
        List<Character> characters = this.userDataUseCase.getPossessionList();
        return characters
                .stream().map(GachaController.CharacterMapper::toOut)
                .collect(Collectors.toList());
    }
}
