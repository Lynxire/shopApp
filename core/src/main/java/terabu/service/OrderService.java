package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.entity.Order;
import terabu.entity.status.OrderStatus;
import terabu.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void updateOrderStatusByUserId(Long userId, OrderStatus status){
        Order orderById = orderRepository.findOrderByUserId(userId);
        if(orderById != null){
            orderById.setStatus(status);
            orderRepository.save(orderById);
        }
        else {
            throw new RuntimeException("Пользователь не найден");
        }
    }

    public void saveOrder(Order order){
        orderRepository.save(order);
    }
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
    public Order getOrderById(Long id){
        return orderRepository.findOrderById(id);
    }
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

}
