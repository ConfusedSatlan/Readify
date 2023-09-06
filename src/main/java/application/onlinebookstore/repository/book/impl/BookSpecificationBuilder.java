package application.onlinebookstore.repository.book.impl;

import application.onlinebookstore.dto.BookSearchParametersDto;
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
        if (titles != null && titles.length > ZERO_LENGTH) {
            spec = spec.and(specificationProviderManager
                    .getSpecificationProvider(TITLE_NAME)
                    .getSpecification(titles));
        }
        String[] authors = searchParameters.authors();
        if (authors != null && authors.length > ZERO_LENGTH) {
            spec = spec.and(specificationProviderManager
                    .getSpecificationProvider(AUTHOR_NAME)
                    .getSpecification(authors));
        }
        return spec;
    }
}
