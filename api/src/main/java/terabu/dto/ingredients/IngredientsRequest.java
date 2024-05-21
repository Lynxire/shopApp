package terabu.dto.ingredients;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IngredientsRequest {
    @NotBlank
    private String name;
    @NotNull
    private Long quantity;
}
