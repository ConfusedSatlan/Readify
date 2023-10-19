package application.onlinebookstore.service;

import application.onlinebookstore.dto.category.CategoryDto;
import application.onlinebookstore.dto.category.CreateCategoryRequestDto;
import application.onlinebookstore.exception.EntityNotFoundException;
import application.onlinebookstore.mapper.CategoryMapper;
import application.onlinebookstore.model.Category;
import application.onlinebookstore.repository.book.CategoryRepository;
import application.onlinebookstore.service.impl.CategoryServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    private static final Long FIRST_ID = 1L;
    private static final String CATEGORY_NAME = "Drama";
    private static final String CATEGORY_DESCRIPTION = "About drama";
    private static final CreateCategoryRequestDto REQUEST_DTO = new CreateCategoryRequestDto();
    private static final Category VALID_CATEGORY = new Category();
    private static final CategoryDto CATEGORY_DTO = new CategoryDto();

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setData() {
        REQUEST_DTO.setName(CATEGORY_NAME);
        REQUEST_DTO.setDescription(CATEGORY_DESCRIPTION);

        VALID_CATEGORY.setId(FIRST_ID);
        VALID_CATEGORY.setName(CATEGORY_NAME);
        VALID_CATEGORY.setDescription(CATEGORY_DESCRIPTION);

        CATEGORY_DTO.setId(FIRST_ID);
        CATEGORY_DTO.setName(CATEGORY_NAME);
        CATEGORY_DTO.setDescription(CATEGORY_DESCRIPTION);
    }

    @Test
    @DisplayName("""
            Verify the correct category was returned when category exist
            """)
    public void getCategory_WithValidUserId_ShouldReturnValidBook() {
        when(categoryRepository.findById(FIRST_ID)).thenReturn(Optional.of(VALID_CATEGORY));
        when(categoryMapper.toDto(VALID_CATEGORY)).thenReturn(Optional.of(CATEGORY_DTO).get());
        CategoryDto categoryById = categoryService.getCategoryById(FIRST_ID);
        assertEquals(FIRST_ID, categoryById.getId());
    }

    @Test
    @DisplayName("""
            Verify the correct throw exception EntityNotFound
            """)
    public void getCategory_WithNegativeOrDoesntExistingId_ShouldReturnThrowException() {
        when(categoryRepository.findById(FIRST_ID)).thenThrow(new EntityNotFoundException("Not exist"));
        Exception exception = assertThrows(
                EntityNotFoundException.class,
                () -> categoryService.getCategoryById(FIRST_ID)
        );
        assertEquals("Not exist", exception.getMessage());
    }
}

