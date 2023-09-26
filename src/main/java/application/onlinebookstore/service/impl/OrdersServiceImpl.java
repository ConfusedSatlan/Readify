package application.onlinebookstore.service.impl;

import application.onlinebookstore.dto.cartitem.CartItemDto;
import application.onlinebookstore.dto.orderitem.CreateOrderItemDto;
import application.onlinebookstore.dto.orderitem.OrderItemDto;
import application.onlinebookstore.dto.orders.CreateOrderDto;
import application.onlinebookstore.dto.orders.OrderDto;
import application.onlinebookstore.dto.shoppingcart.ShoppingCartDto;
import application.onlinebookstore.exception.EntityNotFoundException;
import application.onlinebookstore.mapper.OrderItemMapper;
import application.onlinebookstore.mapper.OrderMapper;
import application.onlinebookstore.model.OrderItem;
import application.onlinebookstore.model.Orders;
import application.onlinebookstore.model.Users;
import application.onlinebookstore.repository.orders.OrdersRepository;
import application.onlinebookstore.service.OrderItemService;
import application.onlinebookstore.service.OrdersService;
import application.onlinebookstore.service.ShoppingCartService;
import application.onlinebookstore.service.UserService;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final OrderItemService orderItemService;
    private final OrderItemMapper orderItemMapper;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> getOrders(Long userId) {
        return ordersRepository.findAllByUserId(userId).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderDto create(CreateOrderDto orderDto, Long userId) {
        Users userById = userService.getUserById(userId);
        Orders order = new Orders();
        order.setUser(userById);
        order.setOrderItems(new HashSet<>());
        order.setOrderDate(LocalDateTime.now());
        order.setShippingAddress(orderDto.shippingAddress());
        order.setStatus(Orders.Status.NOT_COMPLETED);
        order.setTotal(BigDecimal.ZERO);
        order = ordersRepository.save(order);
        ShoppingCartDto shoppingCart = shoppingCartService.getShoppingCart(userId);
        Set<CartItemDto> cartItems = shoppingCart.getCartItems();
        BigDecimal total = new BigDecimal(0);
        Set<OrderItem> orderItems = order.getOrderItems();
        for (CartItemDto cartItem : cartItems) {
            CreateOrderItemDto orderItemDtoParams = new CreateOrderItemDto(
                    order.getId(),
                    cartItem.getBookId(),
                    cartItem.getQuantity()
            );
            OrderItemDto orderItemDto = orderItemService.createOrderItem(orderItemDtoParams);
            total = total.add(orderItemDto.getPrice());
            orderItems.add(orderItemMapper.toModel(orderItemDto));
        }
        order.setTotal(total);
        order.setStatus(Orders.Status.COMPLETED);
        return orderMapper.toDto(ordersRepository.save(order));
    }

    @Override
    public Orders getEntityOrderById(Long id) {
        return ordersRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Order with id: " + id
                        + " not found!")
        );
    }
}
