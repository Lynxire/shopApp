package terabu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
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

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Добавление товара")
    @PostMapping("/add")
    public GoodsResponse addGoods(@RequestBody @Valid GoodsRequest goodsRequest) {
        return goodsService.save(goodsRequest);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Удаление товара по ID")
    @PostMapping("/delete")
    public void deleteGoods(@RequestParam @Min(1) @NotNull Long id) {
        goodsService.deleteById(id);
    }

    @Operation(summary = "Все товары")
    @GetMapping()
    public List<GoodsResponse> getAllGoods(@RequestParam(defaultValue = "0")@Min(0) int page, @RequestParam(defaultValue = "10")@Min(1) @Max(100) int size) {
        return goodsService.findAll(page,size);
    }

    @Operation(summary = "Все товары по определенному типу с использованием пагинации")
    @GetMapping("/findByType")
    public List<GoodsResponse> getAllByType(@RequestParam @NotBlank(message = "Заполните тип") String type,@RequestParam(defaultValue = "0")@Min(0) int page, @RequestParam(defaultValue = "10")@Min(1) @Max(100) int size) {
        return goodsService.findAllByType(type,page,size);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Поиск товаров по ID")
    @GetMapping("/findGoodsById")
    public GoodsResponse findGoodsById(@RequestParam @Min(1) @NotNull Long id) {
        return goodsService.findById(id);
    }

    @Operation(summary = "Поиск товаров по названию")
    @GetMapping("/finGoodsByName")
    public GoodsResponse finGoodsByName(@RequestParam @NotBlank(message = "Заполните имя") String name) {
        return goodsService.findByName(name);
    }

}
