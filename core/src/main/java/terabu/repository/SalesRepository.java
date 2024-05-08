package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.Discount;

import java.util.Optional;

public interface SalesRepository extends JpaRepository<Discount,Long> {
    public Optional<Discount> findByUserId(Long userId);
}
