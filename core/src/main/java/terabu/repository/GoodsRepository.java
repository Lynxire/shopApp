package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import terabu.entity.Goods;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    public Goods save(Goods goods);
    public Goods getById(Long id);
    public List<Goods> findByIdIn(List<Long> id);
    public void deleteById(Long id);
    public List<Goods> findAll();

}
