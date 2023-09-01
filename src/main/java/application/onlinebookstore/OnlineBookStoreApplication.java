package application.onlinebookstore;

import application.onlinebookstore.model.Book;
import application.onlinebookstore.service.BookService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineBookStoreApplication {
    private BookService bookService;

    @Autowired
    public OnlineBookStoreApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Book theGreatGatsby = new Book();
            theGreatGatsby.setTitle("The Great Gatsby");
            theGreatGatsby.setAuthor("F. Scott Fitzgerald");
            theGreatGatsby.setDescription("A classic novel");
            theGreatGatsby.setIsbn("1234567890");
            theGreatGatsby.setPrice(BigDecimal.valueOf(15.99));
            theGreatGatsby.setCoverImage("link");

            bookService.save(theGreatGatsby);

            List<Book> all = bookService.getAll();
            all.forEach(System.out::println);
        };
    }
}
