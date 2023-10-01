INSERT INTO book (id, title, author, isbn, price, description, cover_image)
VALUES
    (1, 'Title One', 'Author 1', '3-4433-4322-1', 15, 'Description One', 'http://example.com/book1.jpg'),
    (2, 'Title Two', 'Author 2', '3-4432-4423-4', 20, 'Description Two', 'http://example.com/book2.jpg'),
    (3, 'Title Three', 'Author 3', '3-4431-4322-1', 9, 'Description Three', 'http://example.com/book3.jpg');

INSERT INTO category (id, name, description)
VALUES
    (1, 'Drama', 'Book about drama'),
    (2, 'History', 'Book about history');

INSERT INTO book_category (book_id, category_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 2);