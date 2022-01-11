package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Override
    public void insert(Book book) {
        bookDao.insert(book);
    }

    @Override
    public void update(Book book) {
        bookDao.update(book);
    }

    @Override
    public void delete(long id) {
        bookDao.delete(id);
    }

    @Override
    public Book getById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.getAll();
    }
}
