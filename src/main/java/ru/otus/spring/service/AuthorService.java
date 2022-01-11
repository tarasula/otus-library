package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorService {

    void insert(String name);

    void update(Author book);

    void delete(long id);

    Author getById(long id);

    List<Author> getAll();

}
