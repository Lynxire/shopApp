package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(schema = "app", name = "bucket")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long count;
    private Double sum;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "app", name = "bucket_goods",
            joinColumns = @JoinColumn(name = "bucket_id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private List<Goods> goods;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "app", name = "bucket_orders",
            joinColumns = @JoinColumn(name = "bucket_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;

}
