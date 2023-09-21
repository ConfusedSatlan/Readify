package application.onlinebookstore.service.impl;

import application.onlinebookstore.dto.orderitem.CreateOrderItemDto;
import application.onlinebookstore.dto.orderitem.OrderItemDto;
import application.onlinebookstore.exception.EntityNotFoundException;
import application.onlinebookstore.mapper.OrderItemMapper;
import application.onlinebookstore.model.Book;
import application.onlinebookstore.model.OrderItem;
import application.onlinebookstore.model.Orders;
import application.onlinebookstore.repository.orderitem.OrderItemRepository;
import application.onlinebookstore.repository.orders.OrdersRepository;
import application.onlinebookstore.service.BookService;
import application.onlinebookstore.service.OrderItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrdersRepository ordersRepository;
    private final BookService bookService;
    private final OrderItemMapper orderItemMapper;

    @Override
    public OrderItemDto createOrderItem(CreateOrderItemDto orderItemDto) {
        Long bookId = orderItemDto.bookId();
        Long orderId = orderItemDto.orderId();
        Integer quantity = orderItemDto.quantity();
        Orders orderById = ordersRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Order with id: " + orderId
                        + " not found!")
        );
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
