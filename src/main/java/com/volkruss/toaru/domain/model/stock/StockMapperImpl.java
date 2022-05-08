package com.volkruss.toaru.domain.model.stock;

import com.volkruss.toaru.domain.dto.stock.StockEntity;

public class StockMapperImpl implements StockMapper{
    @Override
    public StockEntity toStockEntity(int user_id, int character_id) {
        return new StockEntity(user_id,character_id);
    }
}
