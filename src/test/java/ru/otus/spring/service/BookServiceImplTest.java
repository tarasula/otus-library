package ru.otus.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
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
        Book expectedBook = getExpectedBook(1, "Власть тьмы", "Лев Толстой", "Драма");

        when(bookDao.getById(1)).thenReturn(expectedBook);
        var actual = subj.getById(1);
        assertEquals(expectedBook, actual);
    }

    @Test
    void updateBookTest() {
        Book requestBook = getExpectedBook(1, "Власть тьмы", "2", "2");
        Book expectedBook = getExpectedBook(1, "Власть тьмы", "Николай Гоголь", "Комедия");
        doNothing().when(bookDao).update(expectedBook);

        subj.update(requestBook);
        Book actualBook = subj.getById(1);
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @Test
    void insertBookTest() {
        Book requestBook = getExpectedBook(1, "Власть тьмы", "2", "2");
        Book expectedBook = getExpectedBook(1, "Власть тьмы", "Лев Толстой", "Драма");
        doNothing().when(bookDao).insert(expectedBook);

        subj.insert(requestBook);
        Book actual = subj.getById(1);
        assertThat(actual).isEqualTo(expectedBook);
    }

    @Test
    void getAllBooksTest() {

        List<Book> expectedBookList = List.of(
                getExpectedBook(1, "Власть тьмы", "Лев Толстой", "Драма"),
                getExpectedBook(2, "Lets do it", "Николай Гоголь", "Комедия"));

        when(bookDao.getAll()).thenReturn(expectedBookList);
        var actual = subj.getAll();

        assertThat(actual).isEqualTo(expectedBookList);
    }

//    @DisplayName("возвращать список всех сотрудников")
//    @Test
//    void shouldFindAllEmployees() {
//        List<Employee> employees = employeeRepository.findAll();
//        assertThat(employees).hasSize(EMPLOYEES_COUNT);
//    }

    @Test
    void deleteBookTest() {
        subj.delete(1);
        assertThatThrownBy(() -> subj.getById(1))
                .isInstanceOf(EmptyResultDataAccessException.class);
    }

    private Book getExpectedBook(long id, String name, String author, String genre){
        return new Book()
                .setId(id)
                .setName(name)
                .setGenre(genre)
                .setAuthor(author);
    }
}
