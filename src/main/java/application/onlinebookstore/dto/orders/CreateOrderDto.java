package application.onlinebookstore.dto.orders;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreateOrderDto(@NotNull 
                             @Length(min = 5, max = 50)
                             String shippingAddress) {
}
