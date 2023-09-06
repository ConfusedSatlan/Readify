package application.onlinebookstore.repository.book;

import application.onlinebookstore.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    List<Book> findAllByTitleContainsIgnoreCase(String title);

    List<Book> findAllByAuthorContainsIgnoreCase(String author);
}
