package terabu.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import terabu.Main;
import terabu.entity.Goods;
import terabu.entity.status.GoodsType;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = Main.class)
public class GoodsRepositoryTest {
    @Autowired
    private GoodsRepository goodsRepository;


    private final GoodsType type = GoodsType.DONER;

    @Test
    public void isPresentGoodsForName(){
        goodsRepository.save(getGoods());
        Boolean presentName = goodsRepository.isPresentType(type);
        assertTrue(presentName);
    }


    private Goods getGoods(){
        Goods goods = new Goods();
        goods.setId(1L);
        goods.setCount(10L);
        goods.setName("Test");
        goods.setPrice(15.0);
        goods.setIngredients(new ArrayList<>());
        goods.setType(type);
        return goods;
    }

}
