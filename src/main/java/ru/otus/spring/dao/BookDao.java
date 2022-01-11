package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {

    void insert(Book book);

    void update(Book book);

    void delete(long id);

    Book getById(long id);

    List<Book> getAll();

}
