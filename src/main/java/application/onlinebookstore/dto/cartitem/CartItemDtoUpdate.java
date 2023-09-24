package application.onlinebookstore.dto.cartitem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

@Data
public class CartItemDtoUpdate {
    @JsonIgnore
    private Long id;
    @DecimalMin(value = "1",
            message = "Quantity must be more than 0")
    private Integer quantity;
}
