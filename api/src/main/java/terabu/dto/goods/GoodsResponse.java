package terabu.dto.goods;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GoodsResponse implements Serializable {
    private Long id;
    private String name;
    private Long count;
    private Double price;
    private String type;
}
