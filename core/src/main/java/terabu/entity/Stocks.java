package terabu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import terabu.entity.status.StockStatus;

import java.time.LocalDate;
@Data
@Entity
@Table(schema = "app", name = "stocks")
public class Stocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stockName;
    @Enumerated(EnumType.STRING)
    private StockStatus stockStatus;
    private Long sum;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate beginDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;

    @OneToOne()
    @JoinColumn(name = "goodsId")
    private Goods goods;
}
