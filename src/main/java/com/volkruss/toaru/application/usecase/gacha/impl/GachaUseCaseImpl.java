package com.volkruss.toaru.application.usecase.gacha.impl;

import com.volkruss.toaru.application.usecase.gacha.GachaUseCase;
import com.volkruss.toaru.domain.dto.stock.StockEntity;
import com.volkruss.toaru.domain.model.character.Character;
import com.volkruss.toaru.domain.model.gacha.Gacha;
import com.volkruss.toaru.domain.model.user.User;
import com.volkruss.toaru.domain.model.user.UserMapper;
import com.volkruss.toaru.domain.repository.character.CharacterRepository;
import com.volkruss.toaru.domain.repository.stock.StockRepository;
import com.volkruss.toaru.domain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GachaUseCaseImpl implements GachaUseCase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StockRepository stockRepository;

    @Override
    @Transactional
    public List<Character> getCharacters(){
        // ユーザー名を取得する
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // ユーザーの取得
        User user = this.userRepository.findByName(username).toUser();
        // キャラクターの取得
        List<Character> characters =
                this.characterRepository.getCharacters()
                    .stream().map(
                            i -> new Character(i.getId(),i.getName(),i.getLank())
                        ).collect(Collectors.toList());
        // ガチャガチャの作成
        Gacha gacha = new Gacha(characters);
        // コインの利用
        user.useCoin(gacha.needCoind);
        // ガチャの利用（キャラクターの取得）
        List<Character> characterList = gacha.play();

        this.userRepository.updateCoin(this.userMapper.toEntity(user));

        List<StockEntity> stockEntities = characterList.stream().map(i -> {
            return new StockEntity(user.getId(),i.getId());
        }).collect(Collectors.toList());

        this.stockRepository.save(stockEntities);

        return characterList;
    }
}
