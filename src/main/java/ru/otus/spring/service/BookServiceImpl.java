package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.BookDaoImpl;
import ru.otus.spring.domain.Book;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements  BookService{

    private BookDaoImpl bookDao;

    @Autowired
    public BookServiceImpl(BookDaoImpl bookDao) {
        this.bookDao = bookDao;
    }

    @Transactional
    @Override
    public Book insert(Book book) {
        return bookDao.insert(book);
    }

    @Transactional(readOnly = true)
    @Override
    public void update(Book book) {
        bookDao.update(book);
    }

    @Transactional
    @Override
    public void delete(long id) {
        bookDao.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Book getById(long id) {
        return bookDao.getById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }
}
