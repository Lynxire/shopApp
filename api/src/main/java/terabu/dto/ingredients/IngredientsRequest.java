package terabu.dto.ingredients;

import lombok.Data;

@Data
public class IngredientsRequest {
    private String name;
    private Long quantity;
}
