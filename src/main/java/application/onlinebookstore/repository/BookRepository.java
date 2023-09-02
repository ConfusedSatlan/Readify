package application.onlinebookstore.repository;

import application.onlinebookstore.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    Book findByTitle(String title);

    Book findById(Long id);

    List<Book> findAll();
}
