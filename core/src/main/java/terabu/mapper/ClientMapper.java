package terabu.mapper;

import org.mapstruct.Mapper;
import terabu.dto.users.UserRequest;
import terabu.dto.users.UserResponse;
import terabu.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    public Client toEntity(UserRequest userRequest);
    public UserResponse toResponse(Client client);
}
