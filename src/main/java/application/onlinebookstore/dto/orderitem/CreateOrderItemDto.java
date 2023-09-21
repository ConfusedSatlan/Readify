package application.onlinebookstore.dto.orderitem;

public record CreateOrderItemDto(Long orderId,
                                 Long bookId,
                                 Integer quantity) {
}
