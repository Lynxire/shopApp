package terabu.dto.goods;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GoodsRequest {
    @NotBlank
    private String name;
    @NotNull
    private Long count;
    @NotNull
    private Double price;
    @NotBlank
    private String type;
    @NotNull
    private List<Long> ingredientsId;
    @NotNull
    private List<Long> ingredientsQuality;
}
