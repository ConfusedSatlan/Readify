package application.onlinebookstore.repository;

import application.onlinebookstore.dto.book.BookDto;
import application.onlinebookstore.model.Book;
import application.onlinebookstore.model.Category;
import application.onlinebookstore.repository.book.BookRepository;
import application.onlinebookstore.repository.book.CategoryRepository;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    private static final Category VALID_CATEGORY = new Category();
    private static final Book VALID_BOOK = new Book();
    private static final BookDto BOOK_DTO = new BookDto();



    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setData() {
        VALID_CATEGORY.setId(1L);
        VALID_CATEGORY.setName("Drama");
        VALID_CATEGORY.setDescription("Book about drama");

        VALID_BOOK.setId(1L);
        VALID_BOOK.setTitle("Title One");
        VALID_BOOK.setAuthor("Author 1");
        VALID_BOOK.setIsbn("3-4433-4322-1");
        VALID_BOOK.setPrice(BigDecimal.valueOf(15));
        VALID_BOOK.setDescription("Description One");
        VALID_BOOK.setCoverImage("http://example.com/book1.jpg");

        BOOK_DTO.setId(VALID_BOOK.getId());
        BOOK_DTO.setTitle("Title One");
        BOOK_DTO.setAuthor("Author 1");
        BOOK_DTO.setIsbn("3-4433-4322-1");
        BOOK_DTO.setPrice(BigDecimal.valueOf(15));
        BOOK_DTO.setDescription("Description One");
        BOOK_DTO.setCoverImage("http://example.com/book1.jpg");
        BOOK_DTO.setCategoryIds(Set.of(VALID_CATEGORY.getId()));
    }

//    @Test
//    @DisplayName("""
//            Find all books by category id
//            """
//    )
//    @Sql(scripts = "classpath:database/book/add-test-books-and-categories.sql",
//            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    void findAllByCategoryId() {
//        Optional<Book> actual = bookRepository.findAllByCategoryId(1L).stream().findFirst();
//        assertEquals(VALID_BOOK, actual.get());
//    }
}
