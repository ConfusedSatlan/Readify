package application.onlinebookstore.dto.book;

import application.onlinebookstore.validation.Author;
import application.onlinebookstore.validation.Isbn;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

public record CreateBookRequestDto(@NotNull String title,
                                   @Author String author,
                                   @Isbn String isbn,
                                   @NotNull
                                   @Min(0)
                                   BigDecimal price,
                                   String description,
                                   String coverImage,
                                   @NotEmpty
                                   Set<Long> categoryIds) {
}
