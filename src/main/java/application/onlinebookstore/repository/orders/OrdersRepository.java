package application.onlinebookstore.repository.orders;

import application.onlinebookstore.model.Orders;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdersRepository extends JpaRepository<Orders, Long>,
        JpaSpecificationExecutor<Orders> {
    List<Orders> findAllByUserId(Long id);
}
