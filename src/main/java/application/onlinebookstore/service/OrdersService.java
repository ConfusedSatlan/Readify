package application.onlinebookstore.service;

import application.onlinebookstore.dto.orders.CreateOrderDto;
import application.onlinebookstore.dto.orders.OrderDto;
import application.onlinebookstore.model.Orders;
import java.util.List;

public interface OrdersService {

    List<OrderDto> getOrders(Long userId);

    OrderDto create(CreateOrderDto orderDto, Long userId);

    Orders getEntityOrderById(Long id);
}
