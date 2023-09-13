package application.onlinebookstore.repository.book.spec;

import application.onlinebookstore.model.Book;
import application.onlinebookstore.repository.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<Book> {
    private static final String TITLE_NAME = "title";

    @Override
    public String getKey() {
        return TITLE_NAME;
    }

    public Specification<Book> getSpecification(String[] param) {
        return (root, query, criteriaBuilder) -> root.get(TITLE_NAME)
                .in(Arrays.stream(param)
                        .toArray());
    }
}
