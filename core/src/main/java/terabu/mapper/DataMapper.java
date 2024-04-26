package terabu.mapper;

import org.mapstruct.Mapper;
import terabu.dto.data.DataRequest;
import terabu.dto.data.DataResponse;
import terabu.entity.Data;

@Mapper(componentModel = "spring")
public interface DataMapper {
    Data toEntity(DataRequest dataRequest);
    DataResponse toResponse(Data data);
}
