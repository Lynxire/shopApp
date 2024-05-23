package terabu.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.Goods;
import terabu.entity.status.GoodsType;

import java.util.List;
import java.util.Optional;


public interface GoodsRepository extends JpaRepository<Goods, Long> {
    public List<Goods> findByIdIn(List<Long> id);
    public Optional<Goods> findByName(String name);
    public List<Goods> findAllByType(GoodsType type, Pageable pageable);

}
