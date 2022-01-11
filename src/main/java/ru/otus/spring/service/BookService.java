package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {

    void insert(Book book);

    void update(Book book);

    void delete(long id);

    Book getById(long id);

    List<Book> getAll();

}
