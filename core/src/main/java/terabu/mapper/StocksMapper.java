package terabu.mapper;

import org.mapstruct.Mapper;
import terabu.dto.stocks.StocksRequest;
import terabu.dto.stocks.StocksResponse;
import terabu.entity.Stocks;

@Mapper(componentModel = "spring")
public interface StocksMapper {
    public Stocks toEntity(StocksRequest stocksRequest);
    public StocksResponse toResponse(Stocks Stocks);
}
