package application.onlinebookstore.repository.book.spec;

import application.onlinebookstore.model.Book;
import application.onlinebookstore.repository.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;

public class TitleSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "title";
    }

    public Specification<Book> getSpecification(String[] param) {
        return (root, query, criteriaBuilder) -> root.get("title")
                .in(Arrays.stream(param)
                        .toArray());
    }
}
