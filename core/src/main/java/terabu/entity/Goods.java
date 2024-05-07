package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;
import terabu.entity.status.GoodsType;

import java.math.BigDecimal;

@Data
@Entity
@Table(schema = "app", name = "goods")
public class Goods {
    @Id
//    Sequence
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long count;
    private Double price;
    @Enumerated(EnumType.STRING)
    private GoodsType type;
}
