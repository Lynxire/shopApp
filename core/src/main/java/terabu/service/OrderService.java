package terabu.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terabu.dto.orders.OrderResponse;
import terabu.entity.Order;
import terabu.entity.User;
import terabu.entity.status.OrderStatus;
import terabu.exception.orders.OrdersNotFoundException;
import terabu.mapper.OrderMapper;
import terabu.repository.OrderRepository;
import terabu.repository.UserRepositorySpringData;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderResponse> getOrderByUserId(Long userId) {
        List<Order> orderList = orderRepository.findOrderByUserId(userId).stream().toList();
        if (orderList.isEmpty()) {
            throw new OrdersNotFoundException("Нету заказов");
        }

        List<OrderResponse> orderResponseList = new ArrayList<>();
        orderList.forEach(order -> {
            orderResponseList.add(orderMapper.toResponse(order));
        });
        return orderResponseList;
    }

}
