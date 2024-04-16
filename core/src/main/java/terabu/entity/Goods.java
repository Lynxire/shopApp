package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;
import terabu.entity.status.GoodsType;
@Data
@Entity
@Table(schema = "app", name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long count;
    private Long price;
    @Enumerated(EnumType.STRING)
    private GoodsType type;
}
