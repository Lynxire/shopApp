package terabu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import terabu.dto.stocks.StocksRequest;
import terabu.dto.stocks.StocksResponse;
import terabu.service.StocksService;

@RestController
@RequiredArgsConstructor
@RequestMapping("stocks")
@Tag(name = "Контроллер для акций")
public class StocksController {
    private final StocksService stocksService;

    @Operation(summary = "Добавление акции")
    @PostMapping("/add")
    public StocksResponse saveStock(@RequestBody StocksRequest stocksRequest) {
        return stocksService.saveStock(stocksRequest);
    }
    @Operation(summary = "Удаление акции по товару")
    @PostMapping("/delete")
    public void deleteStock(@RequestParam Long goodsId) {
        stocksService.deleteStockByGoodsId(goodsId);
    }

    @Operation(summary = "Поиск акции по товару")
    @GetMapping("/find")
    public StocksResponse findStock(@RequestParam Long goodsId) {
        return stocksService.findStockByGoodsId(goodsId);
    }
}
