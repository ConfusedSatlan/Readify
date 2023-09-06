package application.onlinebookstore.service;

import application.onlinebookstore.dto.BookDto;
import application.onlinebookstore.dto.BookSearchParametersDto;
import application.onlinebookstore.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto bookDto);

    BookDto getBookById(Long id);

    List<BookDto> getBookByTitle(String title);

    List<BookDto> getBookByAuthor(String author);

    List<BookDto> getAll();

    void deleteById(Long id);

    List<BookDto> search(BookSearchParametersDto searchParameters);

    BookDto update(Long id, CreateBookRequestDto book);
}
