package application.onlinebookstore.repository;

import application.onlinebookstore.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
