package terabu.mapper;

import org.mapstruct.Mapper;
import terabu.dto.users.UserRequest;
import terabu.dto.users.UserResponse;
import terabu.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toEntity(UserRequest userRequest);
}
