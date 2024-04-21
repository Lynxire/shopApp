package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.entity.Goods;
import terabu.repository.GoodsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;

    public Goods save(Goods goods) {
        return goodsRepository.save(goods);
    }
    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }
    public Goods findById(Long id) {
        Optional<Goods> optionalGoods = goodsRepository.findById(id);
        List<Goods> list = optionalGoods.stream().toList();
        return list.get(0);
    }
    public void deleteById(Long id) {
        goodsRepository.deleteById(id);
    }
}
