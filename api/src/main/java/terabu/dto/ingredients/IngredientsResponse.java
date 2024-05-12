package terabu.dto.ingredients;

import lombok.Data;

@Data
public class IngredientsResponse {
    private Long id;
    private String name;
    private Long quantity;
}
