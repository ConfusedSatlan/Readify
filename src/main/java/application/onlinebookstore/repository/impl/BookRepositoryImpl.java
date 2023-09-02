package application.onlinebookstore.repository.impl;

import application.onlinebookstore.exception.EntityNotFoundException;
import application.onlinebookstore.model.Book;
import application.onlinebookstore.repository.BookRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public BookRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Book save(Book book) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EntityNotFoundException("Can't insert book " + book, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Book findByTitle(String title) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "FROM Book as b WHERE b.title = :title", Book.class)
                    .setParameter("title", title).getSingleResult();
        } catch (Exception e) {
            throw new EntityNotFoundException("Can't get book by title from db: "
                    + title, e);
        }
    }

    @Override
    public Book findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM Book as b WHERE b.id = :id", Book.class)
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new EntityNotFoundException("Can't get book by id from db: "
                    + id, e);
        }
    }

    @Override
    public List<Book> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "FROM Book", Book.class).getResultList();
        } catch (Exception e) {
            throw new EntityNotFoundException("Can't get all books from db.", e);
        }
    }
}
