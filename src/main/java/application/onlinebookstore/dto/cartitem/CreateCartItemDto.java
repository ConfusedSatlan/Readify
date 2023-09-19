package application.onlinebookstore.dto.cartitem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateCartItemDto(@NotNull
                                Long bookId,
                                @NotNull
                                @Min(1)
                                Integer quantity) {
}
