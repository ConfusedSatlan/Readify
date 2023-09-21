package application.onlinebookstore.service;

import application.onlinebookstore.dto.orderitem.OrderItemDto;
import application.onlinebookstore.dto.orders.CreateOrderDto;
import application.onlinebookstore.dto.orders.OrderDto;
import java.util.List;

public interface OrdersService {

    List<OrderDto> getOrders(Long userId);

    OrderDto create(CreateOrderDto orderDto, Long userId);

    List<OrderItemDto> getOrderItems(Long id, Long userId);

    OrderItemDto getOrderItem(Long orderId, Long itemId, Long userId);
}
