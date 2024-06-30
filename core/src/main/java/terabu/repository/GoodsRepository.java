package terabu.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import terabu.entity.Goods;
import terabu.entity.status.GoodsType;

import java.util.List;
import java.util.Optional;


public interface GoodsRepository extends JpaRepository<Goods, Long> {
    public List<Goods> findByIdIn(List<Long> id);
    public Optional<Goods> findByName(String name);
    public List<Goods> findAllByType(GoodsType type, Pageable pageable);

    @Query("select case when count(g) > 0 then true else false end from Goods g where g.type = :type")
    public Boolean isPresentType(@Param("type") GoodsType type);
}
