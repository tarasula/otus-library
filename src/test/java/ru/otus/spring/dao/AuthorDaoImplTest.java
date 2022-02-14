package ru.otus.spring.dao;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;

import java.util.List;

@DataJpaTest
@Import(AuthorDaoImpl.class)
class AuthorDaoImplTest {

    @Autowired
    AuthorDaoImpl subj;


    @Test
    void returnAllAuthorsTest() {
        List<Author> actualList = subj.getAll();
        assertThat(actualList)
                .containsExactlyInAnyOrder(
                        new Author(1, "Лев Толстой"),
                        new Author(2, "Николай Гоголь")

                );
    }

    @Test
    void getAuthorById() {
        Author actualAuthor = subj.getById(1);
        Author expectedAuthor = new Author(1L, "Лев Толстой");

        assertEquals(actualAuthor, expectedAuthor);
    }

}