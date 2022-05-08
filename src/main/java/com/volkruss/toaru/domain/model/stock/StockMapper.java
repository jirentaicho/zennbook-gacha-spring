package com.volkruss.toaru.domain.model.stock;

import com.volkruss.toaru.domain.dto.stock.StockEntity;

// ラムダを使わないで書きたい場合
public interface StockMapper {
    StockEntity toStockEntity(int user_id, int character_id);
}
