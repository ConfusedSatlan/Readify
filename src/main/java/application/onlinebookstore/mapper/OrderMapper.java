package application.onlinebookstore.mapper;

import application.onlinebookstore.config.MapperConfig;
import application.onlinebookstore.dto.orderitem.OrderItemDto;
import application.onlinebookstore.dto.orders.CreateOrderDto;
import application.onlinebookstore.dto.orders.OrderDto;
import application.onlinebookstore.model.OrderItem;
import application.onlinebookstore.model.Orders;
import java.util.Set;
import org.mapstruct.AfterMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(source = "orderDate", target = "orderDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    OrderDto toDto(Orders order);

    Orders toModel(CreateOrderDto orderDto);

    @IterableMapping(qualifiedByName = "toDto")
    Set<OrderItemDto> mapItems(Set<OrderItem> orderItems);

    @AfterMapping
    default void setUserId(@MappingTarget OrderDto orderDto, Orders order) {
        orderDto.setUserId(order.getUser().getId());
    }
}
