package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "app", name = "ingredients")
public class Ingredients {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredients_id")
    @SequenceGenerator(name = "ingredients_id", sequenceName = "ingredients_seq", allocationSize = 10, schema = "app")
    private Long id;
    private String name;
    private Long quantity;
}
