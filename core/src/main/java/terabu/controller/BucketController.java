package terabu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import terabu.dto.bucket.BucketRequest;
import terabu.dto.bucket.BucketResponse;
import terabu.service.BucketService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("bucket")
@Tag(name = "Контроллер для корзины")
public class BucketController {
    private final BucketService bucketService;
    @Operation(summary = "Добавление в корзину товаров", description = "Добавляет товары в корзину и создает заказ, если у текущего пользователя нету существующего")
    @PostMapping("/add")
    public BucketResponse addOrderAndGoodsByBucket(@RequestBody BucketRequest bucketRequest){
        return bucketService.addOrderAndGoodsByBucket(bucketRequest);
    }

    @Operation(summary = "Оформление заказа в корзине")
    @PostMapping("/complete")
    public void completeBucket(@RequestParam Long userId){
        bucketService.completeBucket(userId);
    }

    @Operation(summary = "Очистка корзины")
    @PostMapping("/clear")
    public void clearBucket(@RequestParam Long userId){
        bucketService.cleanBucket(userId);
    }

    @Operation(summary = "Удаление товара из заказа")
    @PostMapping("/removeGoods")
    public void removeGoods(@RequestBody BucketRequest bucketRequest){
        bucketService.removeGoodsByBucket(bucketRequest);
    }

    @Operation(summary = "Возвращает заказы и товары в корзину пользователю")
    @GetMapping()
    public List<BucketResponse> getBucket(@RequestParam Long userId){
        return bucketService.getBucketByUserId(userId);
    }
}
