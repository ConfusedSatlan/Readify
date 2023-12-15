package application.onlinebookstore.repository;

import application.onlinebookstore.config.CustomPostgreSQLContainer;
import application.onlinebookstore.model.Book;
import application.onlinebookstore.model.Category;
import application.onlinebookstore.repository.book.BookRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    private static final Long ID_TEST = 1L;
    private static final Category VALID_CATEGORY = new Category();
    private static final Book VALID_BOOK = new Book();

    @Autowired
    private BookRepository bookRepository;
    @Container
    private static final PostgreSQLContainer<?> postgresContainer =
            CustomPostgreSQLContainer.getInstance();

    @BeforeEach
    void setData() {
        VALID_CATEGORY.setId(ID_TEST);
        VALID_CATEGORY.setName("Drama");
        VALID_CATEGORY.setDescription("Book about drama");

        VALID_BOOK.setId(ID_TEST);
        VALID_BOOK.setTitle("Title One");
        VALID_BOOK.setAuthor("Author 1");
        VALID_BOOK.setIsbn("3-4433-4322-1");
        VALID_BOOK.setPrice(new BigDecimal("15.00"));
        VALID_BOOK.setDescription("Description One");
        VALID_BOOK.setCoverImage("http://example.com/book1.jpg");
    }

    @Test
    @DisplayName("""
            Find all books by category id
            """
    )
    void findBook_findAllByCategoryId_ShouldEqualsWithValidData() {
        Optional<Book> actual = bookRepository.findAllByCategoryId(ID_TEST).stream().findFirst();
        assertEquals(VALID_BOOK, actual.get());
    }

    @Test
    @DisplayName("""
            Find by id book with categories
            """
    )
    void findBook_findByIdWithCategories_ShouldEqualsWithValidData() {
        Book bookWithCategories = bookRepository
                .findByIdWithCategories(ID_TEST).get();
        Category actual = bookWithCategories
                .getCategories().stream().findFirst().get();
        assertEquals(VALID_CATEGORY, actual);
    }
}
