CREATE TABLE IF NOT EXISTS book (id SERIAL PRIMARY KEY,
                                title VARCHAR(255) NOT NULL,
                                author VARCHAR(255) NOT NULL,
                                isbn VARCHAR(20) NOT NULL,
                                price DECIMAL(10, 2) NOT NULL,
                                description TEXT,
                                cover_image VARCHAR(255),
                                is_deleted BOOLEAN NOT NULL DEFAULT false
    );
CREATE TABLE IF NOT EXISTS category (id SERIAL PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL,
                                    description TEXT,
                                    is_deleted BOOLEAN NOT NULL DEFAULT false
    );
CREATE TABLE IF NOT EXISTS book_category (book_id INT,
                                         category_id INT,
                                         FOREIGN KEY (book_id) REFERENCES book (id),
                                         FOREIGN KEY (category_id) REFERENCES category (id),
                                         PRIMARY KEY (book_id, category_id),
                                         is_deleted BOOLEAN NOT NULL DEFAULT false
    );
INSERT INTO book (title, author, isbn, price, description, cover_image)
VALUES
    ('Title One', 'Author 1', '3-4433-4322-1', 15.00, 'Description One', 'http://example.com/book1.jpg'),
    ('Title Two', 'Author 2', '3-4432-4423-4', 20.00, 'Description Two', 'http://example.com/book2.jpg'),
    ('Title Three', 'Author 3', '3-4431-4322-1', 9.00, 'Description Three', 'http://example.com/book3.jpg');

INSERT INTO category (name, description)
VALUES
    ('Drama', 'Book about drama'),
    ('History', 'Book about history');

INSERT INTO book_category (book_id, category_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 2);
