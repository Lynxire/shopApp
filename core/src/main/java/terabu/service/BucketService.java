package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terabu.dto.bucket.BucketResponse;
import terabu.entity.Bucket;
import terabu.entity.Goods;
import terabu.entity.Order;
import terabu.entity.User;
import terabu.entity.status.OrderStatus;
import terabu.mapper.BucketMapper;
import terabu.repository.BucketRepository;
import terabu.repository.GoodsRepository;
import terabu.repository.OrderRepository;
import terabu.repository.UserRepositorySpringData;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class BucketService {
    private final BucketMapper mapper;
    private final BucketRepository bucketRepository;
    private final GoodsRepository goodsRepository;
    private final OrderRepository orderRepository;
    private final UserRepositorySpringData userRepository;

    @Transactional
    public BucketResponse addOrderAndGoodsByBucket(Long userId, Long goodsId, Long count) {

        Goods goods1 = goodsRepository.findById(goodsId).orElseThrow(() -> new RuntimeException("Товара нет в наличии"));

        User user1 = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователь не авторизован"));
        Order order = orderRepository.findByUserAndStatus(user1, OrderStatus.CREATE).orElseGet
                (() -> {
                    Order newOrder = new Order();
                    newOrder.setUser(user1);
                    newOrder.setStatus(OrderStatus.CREATE);
                    return orderRepository.save(newOrder);
                });


        Long sum = goods1.getPrice() * count;

        Bucket bucket = new Bucket();
        bucket.setId(bucket.getId());
        bucket.setCount(count);
        bucket.setSum(sum);
        bucket.setOrders(Collections.singletonList(order));
        bucket.setGoods(Collections.singletonList(goods1));

        bucketRepository.save(bucket);
        return mapper.toResponse(bucket);


    }




}
