package terabu.dto.goods;

import lombok.Data;

import java.util.List;

@Data
public class GoodsRequest {
    private String name;
    private Long count;
    private Double price;
    private String type;
    private List<Long> ingredientsId;
    private List<Long> ingredientsQuality;
}
