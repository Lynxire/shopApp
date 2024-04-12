package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;

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
    private Long sum;

    @ManyToMany
    @JoinTable(schema = "app", name = "bucket",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "goods_id"))
    private List<Goods> goods;

    @ManyToMany
    @JoinTable(schema = "app", name = "bucket",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "orders_id"))
    private List<Order> orders;

}
