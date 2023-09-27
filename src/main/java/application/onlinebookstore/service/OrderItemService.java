package application.onlinebookstore.service;

import application.onlinebookstore.dto.orderitem.CreateOrderItemDto;
import application.onlinebookstore.dto.orderitem.OrderItemDto;
import java.util.List;

public interface OrderItemService {
    OrderItemDto createOrderItem(CreateOrderItemDto orderItemDto);

    List<OrderItemDto> getOrderItems(Long id);

    OrderItemDto getOrderItem(Long itemId, Long orderId);
}
