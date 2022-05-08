package com.volkruss.toaru.domain.model.gacha;

import com.volkruss.toaru.domain.model.character.Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Gacha {

    // このガチャを回すのに必要なコインの数
    public final int needCoind = 300;

    // このガチャに登場するキャラクター(景品)のリスト
    private List<Character> characters = new ArrayList<>();

    // コンストラクタでガチャに景品を設置しているイメージ
    public Gacha(List<Character> characters){
        this.characters.addAll(characters);
    }

    // ランダムに登場キャラクターから3体を取得する
    public List<Character> play(){
        Collections.shuffle(this.characters);
        return this.characters.stream()
                .limit(3)
                .collect(Collectors.toList());
    }
}
