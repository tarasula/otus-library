package ru.otus.spring.dao;

import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;
import ru.otus.spring.exception.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private final EntityManager em;

    public BookDaoImpl(EntityManager em) {
        this.em = em;
    }

    @SneakyThrows
    @Override
    public Book insert(Book book) {
        try {
            if (book.getId() == 0) {
                em.persist(book);
                return book;
            }
            return em.merge(book);
        } catch (Exception e) {
            throw new DaoException("Unexpected exception during book insertion.", e);
        }
    }

    @Override
    public void update(Book book) {
        Query query = em.createQuery("update Book set name = :name, author = :author_id, genre = :genre_id where id = :id");
        query.setParameter("name", book.getName());
        query.setParameter("author_id", book.getAuthor());
        query.setParameter("genre_id", book.getGenre());
        query.setParameter("id", book.getId());
        query.executeUpdate();
    }

    @Override
    public void delete(long id) {
        Query query = em.createQuery("delete from Book where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Book getById(long id) {
        Query query = em.createQuery("select b.id, b.name, a.name as author, g.name as genre from Book b inner join Author a on b.id = a.id inner join Genre g on b.id = g.id where b.id = :id ");
        query.setParameter("id", id);
        return (Book) query.getSingleResult();
    }

    @Override
    public List<Book> getAll() {
        Query query = em.createQuery("select b.id, b.name, a.name as author, g.name as genre from Book b inner join Author a on b.id = a.id inner join Genre g on b.id = g.id");
        return (List<Book>) query.getResultList();
    }

    @Override
    public List<Book> getAllF() {
        return em.createQuery("select b.id, b.name, b.genre, b.author from Book b").getResultList();
    }
}
