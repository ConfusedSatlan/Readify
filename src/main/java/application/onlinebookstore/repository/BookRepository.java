package application.onlinebookstore.repository;

import application.onlinebookstore.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);

    Optional<Book> findByTitle(String title);

    Optional<Book> findById(Long id);

    List<Book> findAll();
}
