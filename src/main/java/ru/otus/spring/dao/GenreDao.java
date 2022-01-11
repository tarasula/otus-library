package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {

    void insert(String name);

    void update(Genre genre);

    void delete(long id);

    Genre getById(long id);

    List<Genre> getAll();

}
