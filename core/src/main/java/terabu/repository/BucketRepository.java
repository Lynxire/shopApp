package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import terabu.entity.Bucket;

import java.util.List;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {
//    public List<Bucket> findBucketsByOrderId(Long orderId);
    public void deleteAll();
}
