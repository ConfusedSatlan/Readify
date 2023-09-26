package application.onlinebookstore.repository.orders;

import application.onlinebookstore.model.Orders;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface OrdersRepository extends JpaRepository<Orders, Long>,
        JpaSpecificationExecutor<Orders> {
    @Query("SELECT o FROM Orders o JOIN FETCH o.orderItems WHERE o.user.id = :id")
    List<Orders> findAllByUserId(Long id);
}
