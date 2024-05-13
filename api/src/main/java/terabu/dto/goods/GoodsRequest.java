package terabu.dto.goods;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GoodsRequest {
    private String name;
    private Long count;
    private Double price;
    private String type;
    private List<Long> ingredientsId;
    private List<Long> ingredientsQuality;
}
