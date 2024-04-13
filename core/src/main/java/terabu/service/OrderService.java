package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.entity.Order;
import terabu.entity.status.OrderStatus;
import terabu.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order findOrderByUserId(Long id){
        Order orderByUserId = orderRepository.findOrderByUserId(id);
        return orderByUserId;

    }

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
