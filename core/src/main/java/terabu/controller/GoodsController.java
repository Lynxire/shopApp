package terabu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import terabu.dto.goods.GoodsRequest;
import terabu.dto.goods.GoodsResponse;
import terabu.entity.Goods;
import terabu.service.GoodsService;

import java.util.List;

@RestController
@RequestMapping("goods")
@RequiredArgsConstructor
@Tag(name = "Контроллер для товаров")
public class GoodsController {
    private final GoodsService goodsService;

    @Operation(summary = "Добавление товара")
    @PostMapping("/add")
    public GoodsResponse createGoods(@RequestBody GoodsRequest goodsRequest) {
        return goodsService.save(goodsRequest);
    }

    @Operation(summary = "Удаление товара по ID")
    @PostMapping("/delete")
    public void deleteGoods(@RequestParam Long id) {
        goodsService.deleteById(id);
    }

    @Operation(summary = "Все товары")
    @GetMapping()
    public List<GoodsResponse> getAllGoods() {
        return goodsService.findAll();
    }

    @Operation(summary = "Поиск товаров по ID")
    @GetMapping("/findGoodsById")
    public GoodsResponse findGoodsById(@RequestParam Long id) {
        return goodsService.findById(id);
    }

    @Operation(summary = "Поиск товаров по названию")
    @GetMapping("/finGoodsByName")
    public GoodsResponse finGoodsByName(@RequestParam String name) {
        return goodsService.findByName(name);
    }

}
