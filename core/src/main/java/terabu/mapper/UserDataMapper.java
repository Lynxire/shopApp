package terabu.mapper;

import org.mapstruct.Mapper;
import terabu.dto.data.UserDataRequest;
import terabu.dto.data.UserDataResponse;
import terabu.entity.UserData;

@Mapper(componentModel = "spring")
public interface UserDataMapper {
    UserData toEntity(UserDataRequest userDataRequest);
    UserDataResponse toResponse(UserData userData);
}
