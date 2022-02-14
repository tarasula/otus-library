package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @Override
    public Author insert(Author author) {
        return authorDao.insert(author);
    }

    @Transactional
    @Override
    public void update(Author author) {
        authorDao.update(author);
    }

    @Transactional
    @Override
    public void delete(long id) {
        authorDao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Author getById(long id) {
        return authorDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }
}
