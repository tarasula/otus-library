package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public GenreDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Genre> getAll() {
        return em.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    public Genre insert(Genre genre) {
        em.persist(genre);
        return genre;
    }

    @Override
    public void update(Genre genre) {
        em.merge(genre);
    }

    @Override
    public void delete(long id) {
        Genre genre = em.find(Genre.class, id);
        if (genre == null) {
            throw new EntityNotFoundException("Genre with id = " + id + " not found.");
        }
        em.remove(genre);
    }

    @Override
    public Genre getById(long id) {
        return em.find(Genre.class, id);
    }
}
