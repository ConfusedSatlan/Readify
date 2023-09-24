package application.onlinebookstore.service.impl;

import application.onlinebookstore.dto.orderitem.CreateOrderItemDto;
import application.onlinebookstore.dto.orderitem.OrderItemDto;
import application.onlinebookstore.mapper.OrderItemMapper;
import application.onlinebookstore.model.Book;
import application.onlinebookstore.model.OrderItem;
import application.onlinebookstore.model.Orders;
import application.onlinebookstore.repository.orderitem.OrderItemRepository;
import application.onlinebookstore.service.BookService;
import application.onlinebookstore.service.OrderItemService;
import application.onlinebookstore.service.OrdersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private OrdersService ordersService;
    private final BookService bookService;
    private final OrderItemMapper orderItemMapper;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository,
                                BookService bookService,
                                OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.bookService = bookService;
        this.orderItemMapper = orderItemMapper;
    }

    @Autowired
    public void setOrdersService(@Lazy OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Override
    public OrderItemDto createOrderItem(CreateOrderItemDto orderItemDto) {
        Long bookId = orderItemDto.bookId();
        Long orderId = orderItemDto.orderId();
        Integer quantity = orderItemDto.quantity();
        Orders orderById = ordersService.getEntityOrderById(orderId);
        Book bookById = bookService.getEntityBookById(bookId);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(orderById);
        orderItem.setBook(bookById);
        orderItem.setQuantity(quantity);
        orderItem.setPrice(bookById.getPrice());
        return orderItemMapper.toDto(orderItemRepository.save(orderItem));
    }

    @Override
    public List<OrderItemDto> getOrderItems(Long id) {
        return orderItemRepository.findAllByOrderId(id).stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemDto getOrderItem(Long itemId, Long orderId) {
        return orderItemMapper.toDto(orderItemRepository
                .findByIdAndOrderId(itemId, orderId));
    }
}
