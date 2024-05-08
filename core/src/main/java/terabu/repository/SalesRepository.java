package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.Sales;

import java.util.Optional;

public interface SalesRepository extends JpaRepository<Sales,Long> {
    public Optional<Sales> findByUserId(Long userId);
}
