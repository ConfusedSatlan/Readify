package application.onlinebookstore.repository.book.impl;

import application.onlinebookstore.dto.category.CategorySearchParametersDto;
import application.onlinebookstore.model.Category;
import application.onlinebookstore.repository.SpecificationBuilder;
import application.onlinebookstore.repository.SpecificationProviderManager;
import application.onlinebookstore.repository.SpecificationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategorySpecificationBuilder implements
        SpecificationBuilder<Category, CategorySearchParametersDto> {
    private static final String NAME_COLUMN = "name";
    private SpecificationProviderManager<Category>
            specificationProviderManager;

    @Override
    public Specification<Category> build(CategorySearchParametersDto searchParameters) {
        Specification<Category> spec;
        String[] names = searchParameters.names();
        spec = SpecificationUtil
                .getSpecificationByParam(specificationProviderManager, names, NAME_COLUMN);
        return spec;
    }
}
