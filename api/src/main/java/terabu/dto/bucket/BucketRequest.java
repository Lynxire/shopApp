package terabu.dto.bucket;

import lombok.Data;

@Data
public class BucketRequest {
    private Long userId;
    private Long goodsId;
    private Long count;
}
