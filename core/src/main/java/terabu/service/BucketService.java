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
        Goods goodsById = goodsRepository.findById(goodsId).orElseThrow(() -> new RuntimeException("Такого товара нету"));
        if(goodsById.getCount() <= 0 || goodsById.getCount() < count){
            throw new RuntimeException ("Товар закончился или привышено кол-во доступных товаров");
        }
        Long goods1Count = goodsById.getCount();
        goodsById.setCount(goods1Count-count);
        Goods goods = goodsRepository.save(goodsById);

        User user1 = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователь не авторизован"));
        Order order = orderRepository.findByUserAndStatus(user1, OrderStatus.CREATE).orElseGet
                (() -> {
                    Order newOrder = new Order();
                    newOrder.setUser(user1);
                    newOrder.setStatus(OrderStatus.CREATE);
                    return orderRepository.save(newOrder);
                });


        Long sum = goods.getPrice() * count;

        Bucket bucket = new Bucket();
        bucket.setCount(count);
        bucket.setSum(sum);
        bucket.setOrders(Collections.singletonList(order));
        bucket.setGoods(Collections.singletonList(goods));

        bucketRepository.save(bucket);
        return mapper.toResponse(bucket);


    }




}
