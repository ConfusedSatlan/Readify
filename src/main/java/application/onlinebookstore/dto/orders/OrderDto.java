package application.onlinebookstore.dto.orders;

import application.onlinebookstore.dto.orderitem.OrderItemDto;
import application.onlinebookstore.model.Orders;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private String shippingAddress;
    private BigDecimal total;
    private Orders.Status status;
    private LocalDateTime orderDate;
    private Set<OrderItemDto> orderItems;
}
