package application.onlinebookstore.repository.book.spec;

import application.onlinebookstore.model.Book;
import application.onlinebookstore.repository.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    private static final String AUTHOR_NAME = "author";

    @Override
    public String getKey() {
        return AUTHOR_NAME;
    }

    public Specification<Book> getSpecification(String[] param) {
        return (root, query, criteriaBuilder) -> root.get(AUTHOR_NAME)
                .in(Arrays.stream(param)
                        .toArray());
    }
}
