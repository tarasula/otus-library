package ru.otus.spring.dao;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Book;

import java.util.List;

@DisplayName("Dao для работы с пёрсонами должно")
@JdbcTest
@Import(BookDaoImpl.class)
class BookDaoImplTest {

    @Autowired
    private BookDaoImpl subj;

    @Test
    void insertBookTest() {
        Book expectedBook = getExpectedBook(1, "Власть тьмы", 1, 1);
        subj.insert(expectedBook);

        Book actual = subj.getById(1);
        assertThat(actual).isEqualTo(expectedBook);
    }

    @Test
    void getAllBooksTest() {
        Book book1 = getExpectedBook(1, "Власть тьмы", 1, 1);
        Book book2 = getExpectedBook(2, "Lets do it", 2, 2);
        List<Book> expectedData = List.of(book1, book2);
        var actual = subj.getAll();

        assertThat(actual).isEqualTo(expectedData);
    }

    @Test
    void getBookByIdTest() {
        Book expectedBook = getExpectedBook(1, "Власть тьмы", 1, 1);
        var actual = subj.getById(1);

        assertThat(actual).isEqualTo(expectedBook);
    }

    @Test
    void updateBookTest() {
        Book expectedBook = getExpectedBook(1, "Власть света", 1, 1);
        subj.update(expectedBook);
        Book actualBook = subj.getById(1);
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    void deleteBookTest() {
        subj.delete(1);
        assertThatThrownBy(() -> subj.getById(1))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    private Book getExpectedBook(long id, String name, long authorId, long genreId) {
        return new Book()
                .setId(id)
                .setName(name)
                .setGenre(authorId)
                .setAuthor(genreId);
    }

}