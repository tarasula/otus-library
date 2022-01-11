package ru.otus.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Book;

import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class BookServiceImplTest {

    @InjectMocks
    private final BookService subj;

    @Autowired
    public BookServiceImplTest(BookService subj) {
        this.subj = subj;
    }

    @Mock
    private BookDao bookDao;

    @Test
    void getBookByIdTest() {
        Book expectedBook = getExpectedBook(1, "Власть тьмы", 1, 1);

        when(bookDao.getById(1)).thenReturn(expectedBook);
        var actual = subj.getById(1);
        assertEquals(expectedBook, actual);
    }

    @Test
    void updateBookTest() {
        Book expectedBook = getExpectedBook(1, "Власть тьмы", 1, 1);
        doNothing().when(bookDao).update(expectedBook);

        subj.update(expectedBook);
        Book actualBook = subj.getById(1);
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    void insertBookTest() {
        Book expectedBook = getExpectedBook(1, "Власть тьмы", 1, 1);
        doNothing().when(bookDao).insert(expectedBook);

        subj.insert(expectedBook);
        Book actual = subj.getById(1);
        assertThat(actual).isEqualTo(expectedBook);
    }

    @Test
    void getAllBooksTest() {

        List<Book> expectedBookList = List.of(
                getExpectedBook(1, "Власть тьмы", 1, 1),
                getExpectedBook(2, "Lets do it", 2, 2));

        when(bookDao.getAll()).thenReturn(expectedBookList);
        var actual = subj.getAll();

        assertThat(actual).isEqualTo(expectedBookList);
    }

    @Test
    void deleteBookTest() {
        subj.delete(1);
        assertThatThrownBy(() -> subj.getById(1))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    private Book getExpectedBook(long id, String name, long authorId, long genreId){
        return new Book()
                .setId(id)
                .setName(name)
                .setGenre(authorId)
                .setAuthor(genreId);
    }
}
