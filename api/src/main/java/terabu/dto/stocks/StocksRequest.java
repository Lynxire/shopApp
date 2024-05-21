package terabu.dto.stocks;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
public class StocksRequest {
    @NotBlank
    private String stockName;
    @NotNull
    private Long sum;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull
    private LocalDate beginDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull
    private LocalDate endDate;
    @NotNull
    private Long goodsId;
}
