package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.entity.Bucket;
import terabu.entity.Order;
import terabu.entity.status.OrderStatus;
import terabu.repository.BucketRepository;
import terabu.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BucketRepository bucketRepository;

    public void updateOrderStatus(Long userId, OrderStatus status){
        Order orderById = orderRepository.findOrderByUserId(userId);
        if(orderById != null){
            orderById.setStatus(status);
            orderRepository.save(orderById);
        }
        else {
            throw new RuntimeException("Пользователь не найден");
        }
    }
}
