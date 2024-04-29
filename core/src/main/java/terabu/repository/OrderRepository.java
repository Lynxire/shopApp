package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import terabu.entity.Order;
import terabu.entity.User;
import terabu.entity.status.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findOrderByUserId(Long id);
    public Order findOrderById(Long id);
    public Optional<Order> findByUserAndStatus(User user, OrderStatus status);
}
