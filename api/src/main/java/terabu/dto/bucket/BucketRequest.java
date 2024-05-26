package terabu.dto.bucket;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BucketRequest {
    @NotNull
    @Min(value = 1, message = "id пользователя должно быть не меньше 1")
    private Long userId;
    @NotNull
    @Min(value = 1, message = "id товара должно быть не меньше 1")
    private Long goodsId;
    @NotNull
    @Min(value = 1, message = "количество товаров должно быть не меньше 1")
    private Long count;
}
