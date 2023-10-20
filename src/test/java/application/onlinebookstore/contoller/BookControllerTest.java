package application.onlinebookstore.contoller;

import application.onlinebookstore.config.CustomPostgreSQLContainer;
import application.onlinebookstore.dto.book.BookDto;
import application.onlinebookstore.dto.book.CreateBookRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Testcontainers
@WithMockUser(username = "admin", roles = {"ADMIN"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {
    private static final Long ID_TEST = 1L;
    protected static MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Container
    private static final PostgreSQLContainer<?> postgresContainer =
            CustomPostgreSQLContainer.getInstance();

    @BeforeAll
    static void beforeAll(
            @Autowired WebApplicationContext applicationContext,
            @Autowired DataSource dataSource
            ) throws SQLException {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @DisplayName("Create a new book")
    void createBook_ValidRequestDto_Success() throws Exception {
        CreateBookRequestDto requestDto =
                new CreateBookRequestDto(
                        "Book First",
                        "Author First",
                        "4-3422-4322-4",
                        BigDecimal.valueOf(12.43),
                        "about first book",
                        "cover_image",
                        Set.of(ID_TEST));

        BookDto expected = new BookDto()
                .setTitle(requestDto.title())
                .setAuthor(requestDto.author())
                .setIsbn(requestDto.isbn())
                .setPrice(requestDto.price())
                .setDescription(requestDto.description())
                .setCoverImage(requestDto.coverImage())
                .setCategoryIds(requestDto.categoryIds());

        String jsonRequest = objectMapper.writeValueAsString(requestDto);

        MvcResult result = mockMvc.perform(post("/api/books")
                    .content(jsonRequest)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        BookDto actual = objectMapper
                .readValue(result.getResponse().getContentAsString(), BookDto.class);
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getPrice(), actual.getPrice());
    }

    @Test
    @Sql(scripts = "classpath:database/book/add-test-books-and-categories.sql")
    @DisplayName("Get book by title")
    void getBook_ValidRequestDto_Success() throws Exception {
        String titleToSearch = "Title One";

        MvcResult result = mockMvc.perform(get("/api/books/by-title")
                        .param("title", titleToSearch))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        CollectionType collectionType = objectMapper
                .getTypeFactory().constructCollectionType(List.class, BookDto.class);
        List<BookDto> books = objectMapper
                .readValue(result.getResponse().getContentAsString(), collectionType);

        assertNotNull(books);
        assertFalse(books.isEmpty());
        for (BookDto book : books) {
            assertEquals(titleToSearch, book.getTitle());
        }
    }
}
