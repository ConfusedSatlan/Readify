package application.onlinebookstore.controller;

import application.onlinebookstore.dto.book.BookDtoWithoutCategoryIds;
import application.onlinebookstore.dto.category.CategoryDto;
import application.onlinebookstore.dto.category.CategorySearchParametersDto;
import application.onlinebookstore.dto.category.CreateCategoryRequestDto;
import application.onlinebookstore.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Category management", description = "Endpoints for managing categories")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get all category",
            description = "Get a list of all available categories "
                    + "or you can set pageable ?page=?&size=?")
    public List<CategoryDto> getAll(Pageable pageable) {
        return categoryService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id",
            description = "Get category from DB by id")
    public CategoryDto getBookById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/by-name")
    @Operation(summary = "Get category by title",
            description = "Get category from DB by title")
    public List<CategoryDto> getCategoriesByName(@RequestParam String name) {
        return categoryService.getCategoriesByName(name);
    }

    @GetMapping("/{id}/books")
    @Operation(summary = "Get books by category id",
            description = "Get books from DB by category id")
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(@PathVariable Long id) {
        return categoryService.getBooksByCategoryId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "Create a new category",
            description = "Create category from DB in DB")
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryRequestDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete existing category",
            description = "Delete category from DB")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Find categories with several parameters ",
            description = "Find categories by names")
    public List<CategoryDto> search(CategorySearchParametersDto searchParameters) {
        return categoryService.search(searchParameters);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update category",
            description = "Update category in DB")
    public CategoryDto update(@PathVariable Long id,
                              @RequestBody CreateCategoryRequestDto updatedBook) {
        return categoryService.update(id, updatedBook);
    }
}
