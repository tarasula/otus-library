package ru.otus.spring.dao;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;

import java.util.List;

@DataJpaTest
@Import(AuthorDaoImpl.class)
class AuthorDaoImplTest {

    @Autowired
    private AuthorDaoImpl subj;

    @Test
    void insertAuthorTest() {
        Author expected = new Author()
                .setId(1)
                .setName("Лев Толстой");

        subj.insert(expected.getName());

        Author actual = subj.getById(1);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getAllAuthorTest() {
        Author book1 = new Author()
                .setId(1)
                .setName("Лев Толстой");

        Author book2 = new Author()
                .setId(2)
                .setName("Николай Гоголь");

        List<Author> expectedData = List.of(book1, book2);
        var actual = subj.getAll();

        assertThat(actual).isEqualTo(expectedData);
    }

    @Test
    void getAuthorByIdTest() {
        Author expectedBook = new Author()
                .setId(1)
                .setName("Лев Толстой");

        var actual = subj.getById(1);

        assertThat(actual).isEqualTo(expectedBook);
    }

    @Test
    void updateAuthorTest() {
        Author expectedBook = new Author()
                .setId(1)
                .setName("Лев Толстой");

        subj.update(expectedBook);
        Author actualBook = subj.getById(1);
        assertThat(actualBook).isEqualTo(expectedBook);
    }

}