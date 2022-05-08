package com.volkruss.toaru.domain.repository.stock;

import com.volkruss.toaru.domain.dto.character.CharacterEntity;
import com.volkruss.toaru.domain.dto.stock.StockEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockRepository {
    List<CharacterEntity> getUserCharacters(int user_id);
    void save(List<StockEntity> stocks);
}
