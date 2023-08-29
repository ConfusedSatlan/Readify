package application.onlinebookstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "title")
    private String title;
    @Column(nullable = false, name = "author")
    private String author;
    @Column(unique = true, name = "isbn")
    private String isbn;
    @Column(nullable = false, name = "price")
    private BigDecimal price;
    @Column(name = "description")
    private String description;
    @Column(name = "coverImage")
    private String coverImage;
}
