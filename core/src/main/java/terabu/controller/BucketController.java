package terabu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import terabu.dto.bucket.BucketRequest;
import terabu.dto.bucket.BucketResponse;
import terabu.service.BucketService;

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
}
