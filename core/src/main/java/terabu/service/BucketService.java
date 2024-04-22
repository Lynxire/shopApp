package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terabu.entity.Bucket;
import terabu.entity.Goods;
import terabu.entity.Order;
import terabu.entity.User;
import terabu.entity.status.OrderStatus;
import terabu.repository.BucketRepository;
import terabu.repository.GoodsRepository;
import terabu.repository.OrderRepository;
import terabu.repository.UserRepositorySpringData;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BucketService {
    private final BucketRepository bucketRepository;
    private final OrderRepository orderRepository;
    private final UserRepositorySpringData userRepository;
    private final GoodsRepository goodsRepository;

    @Transactional
    public void addOrderAndGoodsByBucket(Long userId, Long goodsId, Long goodsCount, Long goodsSum) {

        User user1 = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Пользователь не авторизован"));

        Goods goods1 = goodsRepository.findById(goodsId).orElseThrow(() -> new RuntimeException("Товара нет в наличии"));
        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(goods1);

        Order order = orderRepository.findByUserAndStatus(user1, OrderStatus.CREATE).orElseGet
                (() -> {
                    Order newOrder = new Order();
                    newOrder.setUser(user1);
                    newOrder.setStatus(OrderStatus.CREATE);
                    return orderRepository.save(newOrder);
                });

        List<Order> orderList = new ArrayList<>();
        orderList.add(order);


        Bucket bucket = new Bucket();
        bucket.setOrders(orderList);
        bucket.setGoods(goodsList);
        bucket.setCount(goodsCount);
        bucket.setSum(goodsSum*goodsCount);


        bucketRepository.save(bucket);

    }

}
