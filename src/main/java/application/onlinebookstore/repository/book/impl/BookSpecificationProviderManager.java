package application.onlinebookstore.repository.book.impl;

import application.onlinebookstore.exception.DataProcessingException;
import application.onlinebookstore.model.Book;
import application.onlinebookstore.repository.SpecificationProvider;
import application.onlinebookstore.repository.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(
                        () -> new DataProcessingException("Can't find correct specification provider "
                                + "for key: " + key)
                );
    }
}
