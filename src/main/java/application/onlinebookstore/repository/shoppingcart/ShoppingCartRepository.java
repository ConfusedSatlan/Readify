package application.onlinebookstore.repository.shoppingcart;

import application.onlinebookstore.model.ShoppingCart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>,
        JpaSpecificationExecutor<ShoppingCart> {
    @Query("SELECT sc FROM ShoppingCart sc JOIN FETCH sc.cartItems WHERE sc.user.id = :id")
    Optional<ShoppingCart> findByUserId(Long id);
}
