package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.dto.orders.OrderResponse;
import terabu.entity.Order;
import terabu.exception.orders.OrdersNotFoundException;
import terabu.mapper.OrderMapper;
import terabu.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderResponse> getOrderByUserId(Long userId) {
        List<Order> orderList = orderRepository.findAllByUserId(userId);
        if (orderList.isEmpty()) {
            throw new OrdersNotFoundException("Нету заказов");
        }
       return orderList.stream().map(orderMapper::toResponse).toList();
    }

}
