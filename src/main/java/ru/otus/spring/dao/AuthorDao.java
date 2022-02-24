package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface AuthorDao {

    Author insert(Author author);

    void update(Author book);

    void delete(long id);

    Author getById(long id);

    List<Author> getAll();

}
