package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terabu.dto.bucket.BucketRequest;
import terabu.dto.bucket.BucketResponse;
import terabu.dto.data.UserDataDTO;
import terabu.dto.users.UserDTO;
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
    private final UserService userService;
    private final DiscountService discountService;
    private final StocksService stocksService;
    private final UserDataService userDataService;

    @LoggerAnnotation
    public BucketResponse addOrderAndGoodsByBucket(BucketRequest bucketRequest) {
        Goods goodsById = goodsRepository.findById(bucketRequest.getGoodsId()).orElseThrow(() -> new GoodsNotFoundException("Такого товара нету"));
        if (goodsById.getCount() <= 0 || goodsById.getCount() < bucketRequest.getCount()) {
            throw new GoodsNotFoundException("Товар закончился или привышено кол-во доступных товаров");
        }
        Long goods1Count = goodsById.getCount();
        goodsById.setCount(goods1Count - bucketRequest.getCount());
        Goods goods = goodsRepository.save(goodsById);

        UserDTO user = userService.findUserById(bucketRequest.getUserId());
        Order order = orderRepository.findByUserIdAndStatus(user.getId(), OrderStatus.CREATE).orElseGet
                (() -> {
                    Order newOrder = new Order();
                    newOrder.setUserId(user.getId());
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
        UserDTO user = userService.findUserById(userId);
        Order order = orderRepository.findByUserIdAndStatus(user.getId(), OrderStatus.CREATE).orElseThrow(() -> new OrdersNotFoundException("Нету заказов"));
        order.setStatus(OrderStatus.COMPLETE);
        UserDataDTO dataDTO = userDataService.findDataByUserId(userId);
        Long ordersData = dataDTO.getOrders();
        dataDTO.setOrders(ordersData + 1);
        userDataService.save(dataDTO);
        orderRepository.save(order);
    }

    public void cleanBucket(Long userId) {
        UserDTO user = userService.findUserById(userId);;
        Order order = orderRepository.findByUserIdAndStatus(user.getId(), OrderStatus.CREATE).orElseThrow(() -> new OrdersNotFoundException("Нету заказов"));
        List<Bucket> bucketList = bucketRepository.findAllByOrdersId(order.getId());
        bucketList.forEach(bucket -> {
            List<Goods> goods = bucket.getGoods();
            goods.forEach(good -> {
                good.setCount(good.getCount() + bucket.getCount());
            });

        });

        bucketRepository.deleteAll(bucketList);
    }

    public void removeGoodsByBucket(BucketRequest bucketRequest) {
        UserDTO user = userService.findUserById(bucketRequest.getUserId());
        Order order = orderRepository.findByUserIdAndStatus(user.getId(), OrderStatus.CREATE).orElseThrow(() -> new OrdersNotFoundException("Нету заказов"));
        List<Bucket> bucketList = bucketRepository.findByGoodsIdAndOrdersId(bucketRequest.getGoodsId(), order.getId());
        bucketList.forEach(bucket -> {
                    Goods goods = goodsRepository.findById(bucketRequest.getGoodsId()).orElseThrow(() -> new GoodsNotFoundException("Товар не найден"));
                    goods.setCount(goods.getCount() + bucket.getCount());
                }
        );
        bucketRepository.deleteAll(bucketList);
    }

    public List<BucketResponse> getBucketByUserId(Long userId) {
        UserDTO user = userService.findUserById(userId);
        Order order = orderRepository.findByUserIdAndStatus(user.getId(), OrderStatus.CREATE).orElseThrow(() -> new RuntimeException("Корзина пустая"));
        List<Bucket> bucketList = bucketRepository.findAllByOrdersId(order.getId());
        List<BucketResponse> bucketResponseList = bucketList.stream().map(bucket -> {
            BucketResponse response = mapper.toResponse(bucket);
            bucket.getGoods().forEach(good -> response.setNameGoods(good.getName()));
            return response;
        }).toList();
        return bucketResponseList;
    }


}
