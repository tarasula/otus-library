package ru.otus.spring.dao;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Genre;

import java.util.List;

@DataJpaTest
@Import(GenreDaoImpl.class)
class GenreDaoImplTest {

    @Autowired
    private GenreDaoImpl subj;

    @Test
    void insertGenreTest() {
        Genre expected = new Genre()
                .setId(1)
                .setName("Драма");

        subj.insert(expected);

        Genre actual = subj.getById(1);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getAllGenreTest() {
        Genre genre1 = new Genre()
                .setId(1)
                .setName("Драма");

        Genre genre2 = new Genre()
                .setId(2)
                .setName("Комедия");

        subj.insert(genre1);
        subj.insert(genre2);

        List<Genre> expectedData = List.of(genre1, genre2);
        var actual = subj.getAll();

        assertThat(actual).isEqualTo(expectedData);
    }

    @Test
    void getGenreByIdTest() {
        Genre expectedGenre = new Genre()
                .setId(1)
                .setName("Драма");

        subj.insert(expectedGenre);
        var actual = subj.getById(1);
        assertThat(actual).isEqualTo(expectedGenre);
    }

}