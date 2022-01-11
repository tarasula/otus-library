package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDaoImpl;
import ru.otus.spring.domain.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private AuthorDaoImpl authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDaoImpl authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public void insert(String name) {
        authorDao.insert(name);
    }

    @Override
    public void update(Author author) {
        authorDao.update(author);
    }

    @Override
    public void delete(long id) {
        authorDao.delete(id);
    }

    @Override
    public Author getById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }
}
