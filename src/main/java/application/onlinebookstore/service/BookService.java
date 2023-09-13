package application.onlinebookstore.service;

import application.onlinebookstore.dto.BookDto;
import application.onlinebookstore.dto.BookSearchParametersDto;
import application.onlinebookstore.dto.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto bookDto);

    BookDto getBookById(Long id);

    List<BookDto> getBooksByTitle(String title);

    List<BookDto> getBooksByAuthor(String author);

    List<BookDto> getAll(Pageable pageable);

    void deleteById(Long id);

    List<BookDto> search(BookSearchParametersDto searchParameters);

    BookDto update(Long id, CreateBookRequestDto book);
}
