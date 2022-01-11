package ru.otus.spring.dao;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import java.util.List;

@JdbcTest
@Import(GenreDaoImpl.class)
class GenreDaoImplTest {

    @Autowired
    private GenreDaoImpl subj;

    @Test
    void insertGenreTest() {
        Genre expected = new Genre()
                .setId(1)
                .setName("Драма");

        subj.insert(expected.getName());

        Genre actual = subj.getById(1);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getAllGenreTest() {
        Genre book1 = new Genre()
                .setId(1)
                .setName("Драма");

        Genre book2 = new Genre()
                .setId(2)
                .setName("Комедия");

        List<Genre> expectedData = List.of(book1, book2);
        var actual = subj.getAll();

        assertThat(actual).isEqualTo(expectedData);
    }

    @Test
    void getGenreByIdTest() {
        Genre expectedBook = new Genre()
                .setId(1)
                .setName("Драма");

        var actual = subj.getById(1);

        assertThat(actual).isEqualTo(expectedBook);
    }

    @Test
    void updateGenreTest() {
        Genre expectedBook = new Genre()
                .setId(1)
                .setName("Драма");

        subj.update(expectedBook);
        Genre actualBook = subj.getById(1);
        assertThat(actualBook).isEqualTo(expectedBook);
    }

}