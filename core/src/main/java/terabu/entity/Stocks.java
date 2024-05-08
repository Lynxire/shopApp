package terabu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import terabu.entity.status.StockStatus;

import java.time.LocalDate;

@Entity
public class Stocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stockName;
    private StockStatus stockStatus;
    private LocalDate beginDate;
    private LocalDate endDate;
}
