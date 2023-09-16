package application.onlinebookstore.service.impl;

import application.onlinebookstore.dto.book.BookDtoWithoutCategoryIds;
import application.onlinebookstore.dto.category.CategoryDto;
import application.onlinebookstore.dto.category.CategorySearchParametersDto;
import application.onlinebookstore.dto.category.CreateCategoryRequestDto;
import application.onlinebookstore.exception.EntityNotFoundException;
import application.onlinebookstore.mapper.BookMapper;
import application.onlinebookstore.mapper.CategoryMapper;
import application.onlinebookstore.model.Category;
import application.onlinebookstore.repository.book.BookRepository;
import application.onlinebookstore.repository.book.CategoryRepository;
import application.onlinebookstore.repository.book.impl.CategorySpecificationBuilder;
import application.onlinebookstore.service.CategoryService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    private final CategoryMapper categoryMapper;
    private final CategorySpecificationBuilder categorySpecificationBuilder;
    private final BookMapper bookMapper;

    @Override
    public CategoryDto save(CreateCategoryRequestDto categoryDto) {
        Category category = categoryRepository.save(categoryMapper.toModel(categoryDto));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find category by id: " + id)
        ));
    }

    @Override
    public List<CategoryDto> getCategoriesByName(String name) {
        return categoryRepository
                .findAllByNameContainingIgnoreCase(name).stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAll(Pageable pageable) {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDto> search(CategorySearchParametersDto searchParameters) {
        Specification<Category> categorySpecification =
                categorySpecificationBuilder.build(searchParameters);
        return categoryRepository
                .findAll(categorySpecification).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto update(Long id, CreateCategoryRequestDto categoryDto) {
        if (categoryRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Category with id: " + id + " not found.");
        }
        Category updatedCategory = categoryMapper.toModel(categoryDto);
        updatedCategory.setId(id);
        return categoryMapper.toDto(categoryRepository.save(updatedCategory));
    }

    @Override
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id) {
        if (bookRepository.findAllByCategoryId(id).isEmpty()) {
            throw new EntityNotFoundException("Can't find books by category id: " + id);
        }
        return bookRepository.findAllByCategoryId(id).stream()
                .map(bookMapper::toDtoWithoutCategories)
                .collect(Collectors.toList());
    }
}
