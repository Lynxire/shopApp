package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.Sales;

public interface SalesRepository extends JpaRepository<Sales,Long> {
}
