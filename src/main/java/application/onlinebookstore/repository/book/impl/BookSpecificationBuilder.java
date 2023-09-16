package application.onlinebookstore.repository.book.impl;

import application.onlinebookstore.dto.book.BookSearchParametersDto;
import application.onlinebookstore.model.Book;
import application.onlinebookstore.repository.SpecificationBuilder;
import application.onlinebookstore.repository.SpecificationProviderManager;
import application.onlinebookstore.repository.SpecificationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements
        SpecificationBuilder<Book, BookSearchParametersDto> {
    private static final String AUTHOR_NAME = "author";
    private static final String TITLE_NAME = "title";
    private SpecificationProviderManager<Book> specificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParameters) {
        Specification<Book> spec;
        String[] titles = searchParameters.titles();
        spec = SpecificationUtil
                .getSpecificationByParam(specificationProviderManager,
                        titles,
                        TITLE_NAME);
        if (Specification.where(null).equals(spec)) {
            String[] authors = searchParameters.authors();
            spec = SpecificationUtil
                    .getSpecificationByParam(specificationProviderManager,
                            authors,
                            AUTHOR_NAME);;
        }
        return spec;
    }
}
