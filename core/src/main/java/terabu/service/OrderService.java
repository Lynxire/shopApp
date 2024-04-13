package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.entity.Order;
import terabu.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order findOrderByUserId(Long id){
        Order orderByUserId = orderRepository.findOrderByUserId(id);
        return orderByUserId;

    }
}
