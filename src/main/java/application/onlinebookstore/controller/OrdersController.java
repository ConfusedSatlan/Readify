package application.onlinebookstore.controller;

import application.onlinebookstore.dto.orderitem.OrderItemDto;
import application.onlinebookstore.dto.orders.CreateOrderDto;
import application.onlinebookstore.dto.orders.OrderDto;
import application.onlinebookstore.model.Users;
import application.onlinebookstore.service.OrderItemService;
import application.onlinebookstore.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Orders management", description = "Endpoints for managing orders")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private final OrderItemService orderItemService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(summary = "Get all user's orders")
    public List<OrderDto> getOrders(Authentication authentication) {
        Users user = (Users) authentication.getPrincipal();
        return ordersService.getOrders(user.getId());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @Operation(summary = "Create a new order")
    public OrderDto createOrder(@RequestBody CreateOrderDto orderDto,
                                Authentication authentication) {
        Users user = (Users) authentication.getPrincipal();
        return ordersService.create(orderDto, user.getId());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}/items")
    @Operation(summary = "Get all items of order")
    public List<OrderItemDto> getOrderItems(@PathVariable Long id) {
        return orderItemService.getOrderItems(id);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Get single item of order by itemId")
    public OrderItemDto getOrderItem(@PathVariable Long orderId,
                                     @PathVariable Long itemId) {
        return orderItemService.getOrderItem(itemId, orderId);
    }
}
