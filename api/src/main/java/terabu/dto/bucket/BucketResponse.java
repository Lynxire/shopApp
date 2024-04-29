package terabu.dto.bucket;

import lombok.Data;

import java.util.List;

@Data
public class BucketResponse {
    private Long id;
    private Long count;
    private Long sum;
}
