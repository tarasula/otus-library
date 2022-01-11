package ru.otus.spring.service;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreService {

    void insert(String name);

    void update(Genre genre);

    void delete(long id);

    Genre getById(long id);

    List<Genre> getAll();

}
