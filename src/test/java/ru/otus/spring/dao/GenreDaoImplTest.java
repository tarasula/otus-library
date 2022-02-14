package ru.otus.spring.dao;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Genre;

import java.util.List;

@DataJpaTest
@Import(GenreDaoImpl.class)
class GenreDaoImplTest {

    @Autowired
    GenreDaoImpl subj;

    @Test
    void getAllGenreTest() {
        List<Genre> actualList = subj.getAll();
        assertThat(actualList)
                .containsExactlyInAnyOrder(
                        new Genre(1, "Драма"),
                        new Genre(2, "Комедия")

                );
    }

    @Test
    void getGenreById() {
        Genre actualGenre = subj.getById(1);
        Genre expectedGenre = new Genre(1L, "Драма");

        assertEquals(actualGenre, expectedGenre);
    }

}