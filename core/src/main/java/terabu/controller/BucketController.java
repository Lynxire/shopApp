package terabu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import terabu.dto.bucket.BucketRequest;
import terabu.dto.bucket.BucketResponse;
import terabu.service.BucketService;

@RestController
@RequiredArgsConstructor
@RequestMapping("bucket")
public class BucketController {
    private final BucketService bucketService;
    @PostMapping("/add")
    public BucketResponse addOrderAndGoodsByBucket(@RequestBody BucketRequest bucketRequest){
        Long userId = bucketRequest.getUserId();
        Long goodsId = bucketRequest.getGoodsId();
        Long goodsCount = bucketRequest.getCount();
        return bucketService.addOrderAndGoodsByBucket(userId, goodsId, goodsCount);
    }
}
