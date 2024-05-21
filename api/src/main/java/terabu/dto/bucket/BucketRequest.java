package terabu.dto.bucket;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BucketRequest {
    @NotNull
    private Long userId;
    @NotNull
    private Long goodsId;
    @NotNull
    private Long count;
}
