package application.onlinebookstore.repository.book.impl;

import application.onlinebookstore.dto.book.BookSearchParametersDto;
import application.onlinebookstore.model.Book;
import application.onlinebookstore.repository.SpecificationBuilder;
import application.onlinebookstore.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private static final int ZERO_LENGTH = 0;
    private static final String AUTHOR_NAME = "author";
    private static final String TITLE_NAME = "title";
    private SpecificationProviderManager<Book> specificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParameters) {
        Specification<Book> spec = Specification.where(null);
        String[] titles = searchParameters.titles();
        if (isParametersValid(titles)) {
            spec = getSpecificationByParam(titles, TITLE_NAME);
        }
        String[] authors = searchParameters.authors();
        if (isParametersValid(authors)) {
            spec = getSpecificationByParam(authors, AUTHOR_NAME);
        }
        return spec;
    }

    private Specification<Book> getSpecificationByParam(String[] items, String name) {
        Specification<Book> spec = Specification.where(null);
        if (isParametersValid(items)) {
            spec = spec.and(specificationProviderManager
                    .getSpecificationProvider(name)
                    .getSpecification(items));
        }
        return spec;
    }

    private boolean isParametersValid(String[] items) {
        return (items != null && items.length > ZERO_LENGTH);
    }
}
