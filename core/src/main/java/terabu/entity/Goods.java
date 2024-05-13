package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;
import terabu.entity.status.GoodsType;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(schema = "app", name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long count;
    private Double price;
    @Enumerated(EnumType.STRING)
    private GoodsType type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "app", name = "goods_ingredients",
            joinColumns = @JoinColumn(name = "goods_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id"))
    private List<Ingredients> ingredients;

}
