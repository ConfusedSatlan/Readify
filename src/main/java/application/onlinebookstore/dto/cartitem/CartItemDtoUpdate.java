package application.onlinebookstore.dto.cartitem;

import jakarta.validation.constraints.Min;

public record CartItemDtoUpdate(@Min(value = 1,
        message = "Quantity must be more than 0")
                                Integer quantity) {
}
