package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;
import terabu.entity.status.GoodsType;

import java.util.List;

@Data
@Entity
@Table(schema = "app", name = "constructor")
public class Constructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long count;
    private Double price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "app", name = "constructor_ingredients",
            joinColumns = @JoinColumn(name = "constructor_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id"))
    private List<Ingredients> ingredients;

    @ManyToOne()
    @JoinColumn(name = "userId")
    private User user;
}
