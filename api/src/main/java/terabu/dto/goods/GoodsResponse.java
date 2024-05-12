package terabu.dto.goods;

import lombok.Data;

import java.util.List;

@Data
public class GoodsResponse {
    private Long id;
    private String name;
    private Long count;
    private Double price;
    private String type;
}
