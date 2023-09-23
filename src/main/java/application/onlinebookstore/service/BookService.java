package application.onlinebookstore.service;

import application.onlinebookstore.dto.book.BookDto;
import application.onlinebookstore.dto.book.BookDtoWithoutCategoryIds;
import application.onlinebookstore.dto.book.BookSearchParametersDto;
import application.onlinebookstore.dto.book.CreateBookRequestDto;
import application.onlinebookstore.model.Book;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto bookDto);

    BookDto getBookById(Long id);

    List<BookDto> getBooksByTitle(String title);

    List<BookDto> getBooksByAuthor(String author);

    List<BookDto> getAll(Pageable pageable);

    void deleteById(Long id);

    List<BookDtoWithoutCategoryIds> search(BookSearchParametersDto searchParameters);

    BookDto update(Long id, CreateBookRequestDto book);

    Book getEntityBookById(Long aLong);
}
