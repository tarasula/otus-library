package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface AuthorDao {

    void insert(String name);

    void update(Author book);

    void delete(long id);

    Author getById(long id);

    List<Author> getAll();

}
