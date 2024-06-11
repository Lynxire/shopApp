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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    /*
    Так как при просмотре всех товаров не обновляется информация, пока жив кэш, то используется очистка кэша (@CacheEvict).
    Если бы использовался только поиск по определенным значения, то лучше использовать @CachePut.
    @CachePut - к примеру мы ищем по id, если в КЭШ не найдено с таким id значение, то он использует goodsService.findById;
     */
    @CacheEvict(value = "goods", allEntries = true, key = "'all'")
    public GoodsResponse addGoods(@RequestBody @Valid GoodsRequest goodsRequest) {
        return goodsService.save(goodsRequest);
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Удаление товара по ID")
    @PostMapping("/delete")
    @CacheEvict(value = "goods", allEntries = true, key = "#id")
    public void deleteGoods(@RequestParam @Min(1) @NotNull Long id) {
        goodsService.deleteById(id);
    }

    @Operation(summary = "Все товары")
    @GetMapping()
    @Cacheable(value = "goods", key = "'all'")
    public List<GoodsResponse> getAllGoods(@RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size) {
        return goodsService.findAll(page, size);
    }

    @Operation(summary = "Все товары по определенному типу с использованием пагинации")
    @GetMapping("/findByType")
    @Cacheable(value = "goods", key = "#type")
    public List<GoodsResponse> getAllByType(@RequestParam @NotBlank(message = "Заполните тип") String type, @RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(defaultValue = "10") @Min(1) @Max(100) int size) {
        return goodsService.findAllByType(type, page, size);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Поиск товаров по ID")
    @GetMapping("/findGoodsById")
    @Cacheable(value = "goods", key = "#id")
    public GoodsResponse findGoodsById(@RequestParam @Min(1) @NotNull Long id) {
        return goodsService.findById(id);
    }

    @Operation(summary = "Поиск товаров по названию")
    @GetMapping("/finGoodsByName")
    @Cacheable(value = "goods", key = "#name")
    public GoodsResponse finGoodsByName(@RequestParam @NotBlank(message = "Заполните имя") String name) {
        return goodsService.findByName(name);
    }

}
