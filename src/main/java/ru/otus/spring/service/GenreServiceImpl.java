package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.GenreDaoImpl;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private GenreDaoImpl genreDao;

    @Autowired
    public GenreServiceImpl(GenreDaoImpl genreDao) {
        this.genreDao = genreDao;
    }

    @Transactional
    @Override
    public Genre insert(Genre genre) {
        return genreDao.insert(genre);
    }

    @Transactional
    @Override
    public void update(Genre genre) {
        genreDao.update(genre);
    }

    @Transactional
    @Override
    public void delete(long id) {
        genreDao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Genre getById(long id) {
        return genreDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }
}
