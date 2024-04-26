package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.Data;

public interface DataRepository extends JpaRepository<Data,Long> {

}
