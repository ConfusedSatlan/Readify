package application.onlinebookstore.repository.book.impl;

import application.onlinebookstore.exception.DataProcessingException;
import application.onlinebookstore.model.Category;
import application.onlinebookstore.repository.SpecificationProvider;
import application.onlinebookstore.repository.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategorySpecificationProviderManager implements SpecificationProviderManager<Category> {
    private final List<SpecificationProvider<Category>> categorySpecificationProviders;

    @Override
    public SpecificationProvider<Category> getSpecificationProvider(String key) {
        return categorySpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(
                        () -> new DataProcessingException("Can't find "
                                + "correct specification provider for key: " + key)
                );
    }
}
