package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import terabu.entity.Goods;

import java.util.List;
import java.util.Optional;


public interface GoodsRepository extends JpaRepository<Goods, Long> {
    public List<Goods> findByIdIn(List<Long> id);
    public Optional<Goods> findByName(String name);

}
