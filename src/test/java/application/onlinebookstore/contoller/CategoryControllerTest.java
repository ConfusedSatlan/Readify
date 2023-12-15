package application.onlinebookstore.contoller;

import application.onlinebookstore.config.CustomPostgreSQLContainer;
import application.onlinebookstore.dto.book.BookDto;
import application.onlinebookstore.dto.category.CategoryDto;
import application.onlinebookstore.dto.category.CreateCategoryRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.sql.SQLException;
import java.util.List;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Testcontainers
@WithMockUser(username = "admin", roles = {"ADMIN"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {
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
    @DisplayName("Create a new category")
    void createCategory_ValidRequestDto_Success() throws Exception {
        CreateCategoryRequestDto requestDto =
                new CreateCategoryRequestDto()
                        .setName("Science")
                        .setDescription("About science!");

        CategoryDto expected = new CategoryDto()
                .setId(ID_TEST)
                .setName(requestDto.getName())
                .setDescription(requestDto.getDescription());

        String jsonRequest = objectMapper.writeValueAsString(requestDto);

        MvcResult result = mockMvc.perform(post("/api/categories")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        CategoryDto actual = objectMapper
                .readValue(result.getResponse().getContentAsString(), CategoryDto.class);
        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    @DisplayName("Get category by name")
    void getCategory_ValidRequestDto_Success() throws Exception {
        String nameToSearch = "Drama";

        MvcResult result = mockMvc.perform(get("/api/categories/by-name")
                        .param("name", nameToSearch))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        CollectionType collectionType = objectMapper
                .getTypeFactory().constructCollectionType(List.class, CategoryDto.class);
        List<CategoryDto> categories = objectMapper
                .readValue(result.getResponse().getContentAsString(), collectionType);

        assertNotNull(categories);
        assertFalse(categories.isEmpty());
        for (CategoryDto category : categories) {
            assertEquals(nameToSearch, category.getName());
        }
    }
}
