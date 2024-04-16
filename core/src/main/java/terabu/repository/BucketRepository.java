package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.Bucket;

import java.util.List;


public interface BucketRepository extends JpaRepository<Bucket, Long> {
    public List<Bucket> findAllByOrdersId(Long orderId);
}
