package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.dto.users.UserDTO;
import terabu.entity.Order;
import terabu.entity.status.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findAllByUserId(Long userId);
    public Order findOrderById(Long id);
    public Optional<Order> findByUserIdAndStatus(Long userId, OrderStatus status);
}
