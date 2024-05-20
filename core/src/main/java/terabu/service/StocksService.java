package terabu.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.dto.stocks.StocksRequest;
import terabu.dto.stocks.StocksResponse;
import terabu.entity.Goods;
import terabu.entity.Stocks;
import terabu.entity.status.StockStatus;
import terabu.exception.goods.GoodsNotFoundException;
import terabu.exception.stocks.StocksNotFoundException;
import terabu.mapper.StocksMapper;
import terabu.repository.GoodsRepository;
import terabu.repository.StocksRepository;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
@Transactional
public class StocksService {
    private final StocksRepository stocksRepository;
    private final StocksMapper stocksMapper;
    private final GoodsRepository goodsRepository;

    public StocksResponse saveStock(StocksRequest stocksRequest) {
        Stocks stocks = stocksMapper.toEntity(stocksRequest);
        Goods goods = goodsRepository.findById(stocksRequest.getGoodsId()).orElseThrow(() -> new GoodsNotFoundException("Товар не найден"));
        stocks.setStockStatus(StockStatus.ACTIVE);
        stocks.setGoods(goods);
        stocksRepository.save(stocks);
        StocksResponse response = stocksMapper.toResponse(stocks);
        response.setGoodsId(stocksRequest.getGoodsId());
        return response;
    }

    public Long calculateStocks(Long goodsId){
        Stocks stocks = stocksRepository.findById(goodsId).orElse(null);
        if(stocks == null){
            return null;
        }

        if(stocks.getEndDate().isAfter(LocalDate.now()) || stocks.getEndDate().isEqual(LocalDate.now())){
            return stocks.getSum();
        }
        if(stocks.getEndDate().isBefore(LocalDate.now())){
            stocks.setStockStatus(StockStatus.COMPLETE);
            return null;
        }
        return null;
    }

    public void deleteStockByGoodsId(Long goodsId){
        stocksRepository.deleteAllByGoodsId(goodsId);
    }

    public StocksResponse findStockByGoodsId(Long goodsId){
        Stocks stocks = stocksRepository.findByGoodsId(goodsId).orElseThrow(() -> new StocksNotFoundException("Акции не найдены"));
        StocksResponse response = stocksMapper.toResponse(stocks);
        response.setGoodsId(goodsId);
        return response;
    }
}
