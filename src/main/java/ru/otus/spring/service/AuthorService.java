package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorService {

    Author insert(Author author);

    void update(Author book);

    void delete(long id);

    Author getById(long id);

    List<Author> getAll();

}
