package application.onlinebookstore.repository.book.impl;

import application.onlinebookstore.consthelper.ArraysConst;
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
    private SpecificationProviderManager<Book> specificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParameters) {
        Specification<Book> spec = Specification.where(null);
        String[] titles = searchParameters.titles();
        if (titles != null && titles.length > ArraysConst.ZERO_LENGTH) {
            spec = spec.and(specificationProviderManager
                    .getSpecificationProvider("title")
                    .getSpecification(titles));
        }
        String[] authors = searchParameters.authors();
        if (authors != null && authors.length > ArraysConst.ZERO_LENGTH) {
            spec = spec.and(specificationProviderManager
                    .getSpecificationProvider("author")
                    .getSpecification(authors));
        }
        return spec;
    }
}
