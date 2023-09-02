package application.onlinebookstore.service;

import application.onlinebookstore.dto.BookDto;
import application.onlinebookstore.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto bookDto);

    BookDto getBookById(Long id);

    BookDto getBookByTitle(String title);

    List<BookDto> getAll();
}
