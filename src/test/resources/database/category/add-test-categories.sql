CREATE TABLE IF NOT EXISTS category (id SERIAL PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL,
                                    description TEXT,
                                    is_deleted BOOLEAN NOT NULL DEFAULT false
    );

INSERT INTO category (name, description)
VALUES
    ('Drama', 'Book about drama'),
    ('History', 'Book about history');
