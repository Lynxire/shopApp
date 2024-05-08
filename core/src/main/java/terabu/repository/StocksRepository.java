package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.Stocks;

import java.util.Optional;

public interface StocksRepository extends JpaRepository<Stocks, Long> {
    public void deleteAllByGoodsId(Long goodsId);
    public Optional<Stocks> findByGoodsId(Long goodsId);
}
