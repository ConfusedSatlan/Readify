package application.onlinebookstore.repository;

import application.onlinebookstore.config.CustomPostgreSQLContainer;
import application.onlinebookstore.model.Book;
import application.onlinebookstore.model.Category;
import application.onlinebookstore.repository.book.CategoryRepository;
import java.util.Optional;
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
public class CategoryRepositoryTest {
    private static final Category VALID_CATEGORY = new Category();
    private static final Long ID_TEST = 1L;
    private static final String VALID_CATEGORY_NAME = "Drama";

    @Autowired
    private CategoryRepository categoryRepository;
    @Container
    private static final PostgreSQLContainer<?> postgresContainer =
            CustomPostgreSQLContainer.getInstance();

    @BeforeEach
    void setData() {
        VALID_CATEGORY.setId(ID_TEST);
        VALID_CATEGORY.setName("Drama");
        VALID_CATEGORY.setDescription("Book about drama");
    }

    @Test
    @DisplayName("""
            Find all categories by name
            """
    )
    void findCategory_findAllByName_ShouldEqualsWithValidData() {
        Optional<Category> actual = categoryRepository
                .findAllByNameContainingIgnoreCase(VALID_CATEGORY_NAME)
                .stream().findFirst();
        assertEquals(VALID_CATEGORY, actual.get());
    }
}
