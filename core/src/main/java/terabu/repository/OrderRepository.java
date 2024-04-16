package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import terabu.entity.Order;
public interface OrderRepository extends JpaRepository<Order, Long> {
    public Order save(Order order);
    public Order findOrderByUserId(Long id);
    public Order findOrderById(Long id);
}
