package terabu.dto.goods;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GoodsRequest {
    @NotBlank(message = "пустое поле названия товара")
    private String name;
    @NotNull
    @Min(1)
    private Long count;
    @NotNull
    private Double price;
    @NotBlank(message = "Не указан тип товара")
    private String type;
    @NotNull
    private List<Long> ingredientsId;
    @NotNull
    private List<Long> ingredientsQuality;
}
