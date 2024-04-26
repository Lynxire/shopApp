package terabu.mapper;

import org.mapstruct.Mapper;
import terabu.dto.users.UserRequest;
import terabu.dto.users.UserResponse;
import terabu.entity.Admin;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    public Admin toEntity(UserRequest userRequest);
    public UserResponse toResponse(Admin admin);
}
