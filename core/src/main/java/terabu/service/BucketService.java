package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terabu.dto.bucket.BucketRequest;
import terabu.dto.bucket.BucketResponse;
import terabu.entity.Bucket;
import terabu.entity.Goods;
import terabu.entity.Order;
import terabu.entity.User;
import terabu.entity.status.OrderStatus;
import terabu.logger.LoggerAnnotation;
import terabu.mapper.BucketMapper;
import terabu.repository.BucketRepository;
import terabu.repository.GoodsRepository;
import terabu.repository.OrderRepository;
import terabu.repository.UserRepositorySpringData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Transactional
@Service
@RequiredArgsConstructor
public class BucketService {
    private final BucketMapper mapper;
    private final BucketRepository bucketRepository;
    private final GoodsRepository goodsRepository;
    private final OrderRepository orderRepository;
    private final UserRepositorySpringData userRepository;

    @LoggerAnnotation
    public BucketResponse addOrderAndGoodsByBucket(BucketRequest bucketRequest) {
        Goods goodsById = goodsRepository.findById(bucketRequest.getGoodsId()).orElseThrow(() -> new RuntimeException("Такого товара нету"));
        if (goodsById.getCount() <= 0 || goodsById.getCount() < bucketRequest.getCount()) {
            throw new RuntimeException("Товар закончился или привышено кол-во доступных товаров");
        }
        Long goods1Count = goodsById.getCount();
        goodsById.setCount(goods1Count - bucketRequest.getCount());
        Goods goods = goodsRepository.save(goodsById);

        User user = userRepository.findById(bucketRequest.getUserId()).orElseThrow(() -> new RuntimeException("Пользователь не авторизован"));
        Order order = orderRepository.findByUserAndStatus(user, OrderStatus.CREATE).orElseGet
                (() -> {
                    Order newOrder = new Order();
                    newOrder.setUser(user);
                    newOrder.setStatus(OrderStatus.CREATE);
                    return orderRepository.save(newOrder);
                });


        Long sum = goods.getPrice() * bucketRequest.getCount();

        Bucket bucket = new Bucket();
        bucket.setCount(bucketRequest.getCount());
        bucket.setSum(sum);
        bucket.setOrders(Collections.singletonList(order));
        bucket.setGoods(Collections.singletonList(goods));

        bucketRepository.save(bucket);
        return mapper.toResponse(bucket);
    }

    public void completeBucket(Long userId) {
        User user = userRepository.findById(userId).get();
        Order order = orderRepository.findByUserAndStatus(user, OrderStatus.CREATE).orElseThrow(() -> new RuntimeException("Нету заказов"));
        order.setStatus(OrderStatus.COMPLETE);
        orderRepository.save(order);
    }

    public void cleanBucket(Long userId) {
        User user = userRepository.findById(userId).get();
        Order order = orderRepository.findByUserAndStatus(user, OrderStatus.CREATE).orElseThrow(() -> new RuntimeException("Нету заказов"));
        List<Bucket> bucketList = bucketRepository.findAllByOrdersId(order.getId());
        bucketRepository.deleteAll(bucketList);
    }

    public void removeGoodsByBucket(BucketRequest bucketRequest) {
        User user = userRepository.findById(bucketRequest.getUserId()).get();
        Order order = orderRepository.findByUserAndStatus(user, OrderStatus.CREATE).orElseThrow(() -> new RuntimeException("Нету заказов"));
        Bucket bucket = bucketRepository.findByGoodsIdAndOrdersId(bucketRequest.getGoodsId(), order.getId());
        bucketRepository.delete(bucket);
    }

    public List<BucketResponse> getBucketByUserId(Long userId) {
        User user = userRepository.findById(userId).get();
        Order order = orderRepository.findByUserAndStatus(user, OrderStatus.CREATE).orElseThrow(() -> new RuntimeException("Корзина пустая"));
        List<Bucket> bucketList = bucketRepository.findAllByOrdersId(order.getId());
        List<BucketResponse> bucketResponseList = new ArrayList<>();
        bucketList.forEach(bucket -> {
            BucketResponse bucketResponse = mapper.toResponse(bucket);
            List<Goods> goodsList = bucket.getGoods().stream().toList();
            goodsList.forEach(goods -> {
                bucketResponse.setNameGoods(goods.getName());
                bucketResponseList.add(bucketResponse);
            });
        });

        return bucketResponseList;
    }



}
