package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terabu.dto.bucket.BucketRequest;
import terabu.dto.bucket.BucketResponse;
import terabu.entity.*;
import terabu.entity.status.OrderStatus;
import terabu.exception.goods.GoodsNotFoundException;
import terabu.exception.orders.OrdersNotFoundException;
import terabu.logger.LoggerAnnotation;
import terabu.mapper.BucketMapper;
import terabu.repository.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class BucketService {
    private final BucketMapper mapper;
    private final BucketRepository bucketRepository;
    private final GoodsRepository goodsRepository;
    private final OrderRepository orderRepository;
    private final UserRepositorySpringData userRepository;
    private final DiscountService discountService;
    private final StocksService stocksService;
    private final UserDataRepository userDataRepository;

    @LoggerAnnotation
    public BucketResponse addOrderAndGoodsByBucket(BucketRequest bucketRequest) {
        Goods goodsById = goodsRepository.findById(bucketRequest.getGoodsId()).orElseThrow(() -> new GoodsNotFoundException("Такого товара нету"));
        if (goodsById.getCount() <= 0 || goodsById.getCount() < bucketRequest.getCount()) {
            throw new GoodsNotFoundException("Товар закончился или привышено кол-во доступных товаров");
        }
        Long goods1Count = goodsById.getCount();
        goodsById.setCount(goods1Count - bucketRequest.getCount());
        Goods goods = goodsRepository.save(goodsById);

        User user = userRepository.findById(bucketRequest.getUserId()).orElseThrow(() -> new UsernameNotFoundException("Пользователь не авторизован"));
        Order order = orderRepository.findByUserAndStatus(user, OrderStatus.CREATE).orElseGet
                (() -> {
                    Order newOrder = new Order();
                    newOrder.setUser(user);
                    newOrder.setStatus(OrderStatus.CREATE);
                    return orderRepository.save(newOrder);
                });

        Long discountPercent = 0L;
        if(stocksService.calculateStocks(bucketRequest.getGoodsId()) != null ){
            discountPercent = stocksService.calculateStocks(bucketRequest.getGoodsId());
        }
        else {
            discountPercent = discountService.calculatingDiscount(bucketRequest.getUserId());
        }

        Double discount = (goods.getPrice() * bucketRequest.getCount()) * discountPercent / 100;
        Double sum = (goods.getPrice() * bucketRequest.getCount()) - discount;
        Bucket bucket = new Bucket();
        bucket.setCount(bucketRequest.getCount());
        bucket.setSum(sum);
        bucket.setOrders(Collections.singletonList(order));
        bucket.setGoods(Collections.singletonList(goods));

        bucketRepository.save(bucket);
        return mapper.toResponse(bucket);
    }

    public void completeBucket(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        Order order = orderRepository.findByUserAndStatus(user, OrderStatus.CREATE).orElseThrow(() -> new OrdersNotFoundException("Нету заказов"));
        order.setStatus(OrderStatus.COMPLETE);
        UserData userData = userDataRepository.findByUserId(userId);
        Long ordersData = userData.getOrders();
        userData.setOrders(ordersData + 1);
        userDataRepository.save(userData);
        orderRepository.save(order);
    }

    public void cleanBucket(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));;
        Order order = orderRepository.findByUserAndStatus(user, OrderStatus.CREATE).orElseThrow(() -> new OrdersNotFoundException("Нету заказов"));
        List<Bucket> bucketList = bucketRepository.findAllByOrdersId(order.getId());
        bucketList.forEach(bucket -> {
            List<Goods> goods = bucket.getGoods();
            goods.forEach(good -> {
//                Goods goodsCount = goodsRepository.findById(good.getId()).get();
//                goodsCount.setCount(goodsCount.getCount() + bucket.getCount());

                good.setCount(good.getCount() + bucket.getCount());
            });

        });

        bucketRepository.deleteAll(bucketList);
    }

    public void removeGoodsByBucket(BucketRequest bucketRequest) {
        //Проверка
        User user = userRepository.findById(bucketRequest.getUserId()).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        Order order = orderRepository.findByUserAndStatus(user, OrderStatus.CREATE).orElseThrow(() -> new OrdersNotFoundException("Нету заказов"));
        List<Bucket> bucketList = bucketRepository.findByGoodsIdAndOrdersId(bucketRequest.getGoodsId(), order.getId());
        bucketList.forEach(bucket -> {
                    Goods goods = goodsRepository.findById(bucketRequest.getGoodsId()).orElseThrow(() -> new GoodsNotFoundException("Товар не найден"));
                    goods.setCount(goods.getCount() + bucket.getCount());
                }
        );
        bucketRepository.deleteAll(bucketList);
    }

    public List<BucketResponse> getBucketByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        Order order = orderRepository.findByUserAndStatus(user, OrderStatus.CREATE).orElseThrow(() -> new RuntimeException("Корзина пустая"));
        List<Bucket> bucketList = bucketRepository.findAllByOrdersId(order.getId());
        List<BucketResponse> bucketResponseList = bucketList.stream().map(bucket -> {
            BucketResponse response = mapper.toResponse(bucket);
            bucket.getGoods().forEach(good -> response.setNameGoods(good.getName()));
            return response;
        }).toList();
//
//        List<BucketResponse> bucketResponseList = new ArrayList<>();
//        bucketList.forEach(bucket -> {
//            BucketResponse bucketResponse = mapper.toResponse(bucket);
//            List<Goods> goodsList = bucket.getGoods().stream().toList();
//            goodsList.forEach(goods -> {
//                bucketResponse.setNameGoods(goods.getName());
//                bucketResponseList.add(bucketResponse);
//            });
//        });

        return bucketResponseList;
    }


}
