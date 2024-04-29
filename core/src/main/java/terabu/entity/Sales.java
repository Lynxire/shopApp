package terabu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table(schema = "app", name = "sales")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Long count;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "goodsId")
    private Goods goods;
}
