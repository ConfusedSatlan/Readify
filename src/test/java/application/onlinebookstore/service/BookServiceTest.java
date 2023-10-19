package application.onlinebookstore.service;

import application.onlinebookstore.exception.EntityNotFoundException;
import application.onlinebookstore.mapper.BookMapper;
import application.onlinebookstore.model.Book;
import application.onlinebookstore.repository.book.BookRepository;
import application.onlinebookstore.service.impl.BookServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
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
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    @DisplayName("""
            Verify the correct book was returned when book exists
            """)
    public void getBook_WithValidUserId_ShouldReturnValidBook() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Title 1");
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        Book result = bookService.getEntityBookById(bookId);
        assertEquals("Title 1", result.getTitle());
    }

    @Test
    @DisplayName("""
            Verify the correct throw exception EntityNotFound
            """)
    public void getBook_WithNegativeOrDoesntExistingId_ShouldReturnThrowException() {
        Long bookId = -100L;
        Book book = new Book();
        book.setId(bookId);
        book.setTitle("Title 1");
        when(bookRepository.findById(bookId)).thenThrow(new EntityNotFoundException("Not exist"));
        Exception exception = assertThrows(
                EntityNotFoundException.class,
                () -> bookService.getEntityBookById(bookId)
        );
        assertEquals("Not exist", exception.getMessage());
    }
}
