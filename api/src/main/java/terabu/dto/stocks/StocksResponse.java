package terabu.dto.stocks;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
public class StocksResponse {
    private String stockName;
    private String stockStatus;
    private Long sum;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate beginDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;
    private Long goodsId;
}
