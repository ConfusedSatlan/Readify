package application.onlinebookstore.repository;

import application.onlinebookstore.dto.book.BookSearchParametersDto;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T, Y> {
    Specification<T> build(Y searchParameters);
}
