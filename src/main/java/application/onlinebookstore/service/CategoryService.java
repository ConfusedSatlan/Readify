package application.onlinebookstore.service;

import application.onlinebookstore.dto.book.BookDtoWithoutCategoryIds;
import application.onlinebookstore.dto.category.CategoryDto;
import application.onlinebookstore.dto.category.CategorySearchParametersDto;
import application.onlinebookstore.dto.category.CreateCategoryRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDto save(CreateCategoryRequestDto categoryDto);

    CategoryDto getCategoryById(Long id);

    List<CategoryDto> getCategoriesByName(String name);

    List<CategoryDto> getAll(Pageable pageable);

    void deleteById(Long id);

    List<CategoryDto> search(CategorySearchParametersDto searchParameters);

    CategoryDto update(Long id, CreateCategoryRequestDto categoryDto);

    List<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id);
}
