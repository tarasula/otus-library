package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public AuthorDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Author> getAll() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    public Author insert(Author author) {
        em.persist(author);
        return author;
    }

    @Override
    public void update(Author author) {
        em.merge(author);
    }

    @Override
    public void delete(long id) {
        Author author = em.find(Author.class, id);
        if (author == null) {
            throw new EntityNotFoundException("Author with id = " + id + " not found.");
        }
        em.remove(author);
    }

    @Override
    public Author getById(long id) {
        return em.find(Author.class, id);
    }
}
