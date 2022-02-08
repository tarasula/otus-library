package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private final EntityManager em;

    public AuthorDaoImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public Author getById(long id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> getAll() {
        return em.createQuery("select * from author", Author.class).getResultList();
    }

    @Override
    public void insert(String name) {
        em.persist(new Author().setName(name));
    }

    @Override
    public void update(Author author) {
        Query query = em.createQuery("update author set name = :name where id = :id");
        query.setParameter("name", author.getName());
        query.setParameter("id", author.getId());
        query.executeUpdate();
    }

    @Override
    public void delete(long id) {
        Query query = em.createQuery("delete from author where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
