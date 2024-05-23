package terabu.dto.ingredients;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IngredientsRequest {
    @NotBlank(message = "Не указано название ингредиента")
    private String name;
    @NotNull @Min(1)
    private Long quantity;
}
