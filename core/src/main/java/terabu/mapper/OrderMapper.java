package terabu.mapper;

import org.mapstruct.Mapper;
import terabu.dto.orders.OrderResponse;
import terabu.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    public OrderResponse toResponse(Order order);
}
