package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public BookDaoImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Book> getAll() {
        return em.createQuery("select b from Book b join fetch b.author join fetch b.genre", Book.class).getResultList();
    }

    @Override
    public Book insert(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    public Book getById(long id) {
        return em.createQuery("select b from Book b join fetch b.author join fetch b.genre where b.id = :id", Book.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void update(Book book) {
        em.merge(book);
    }

    @Override
    public void delete(long id) {
        Book b = em.find(Book.class, id);
        if (b == null) {
            throw new EntityNotFoundException("Book with id = " + id + " not found.");
        }
        em.remove(b);
    }
}
