package application.onlinebookstore.repository.orderitem;

import application.onlinebookstore.model.OrderItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>,
        JpaSpecificationExecutor<OrderItem> {
    List<OrderItem> findAllByOrderId(Long id);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.id = :id AND oi.order.id = :orderId")
    OrderItem findByIdAndOrderId(Long id, Long orderId);
}
