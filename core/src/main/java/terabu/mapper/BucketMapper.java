package terabu.mapper;

import org.mapstruct.Mapper;
import terabu.dto.bucket.BucketRequest;
import terabu.dto.bucket.BucketResponse;
import terabu.entity.Bucket;
@Mapper(componentModel = "spring")
public interface BucketMapper {
    public BucketResponse toResponse(Bucket bucket);
}
