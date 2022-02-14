package ru.otus.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.BookDaoImpl;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.List;

@DataJpaTest
@Import({BookServiceImpl.class, BookDaoImpl.class})
class BookServiceImplTest {

    @Autowired
    private BookServiceImpl bookService;

    @Test
    void getBookByIdServiceTest() {
        Book book = bookService.getById(1L);
        assertThat(book).isNotNull();
    }

    @Test
    void getAllBooksServiceTest() {
        List<Book> books = bookService.getAll();
        assertEquals(books.size(), 2);
    }

    @Test
    void deleteBookTest() {
        List<Book> bookList = bookService.getAll();
        assertEquals(bookList.size(), 2);

        bookService.delete(1L);

        List<Book> bookListDeletedOne = bookService.getAll();
        assertEquals(bookListDeletedOne.size(), 1);

    }

    @Test
    void insertBookTest() {
        Author a3 = new Author(3L, "Новый Автор");
        Genre g3 = new Genre(3L, "Новый Жанр");

        Book actualBook = new Book()
//                .setId(3L)
                .setName("Ночь")
                .setAuthor(a3)
                .setGenre(g3);

        Book savedBook = bookService.insert(actualBook);

        assertEquals(actualBook.getId(), savedBook.getId());
    }

    @Test
    void updateBookTest() {
        Author a3 = new Author(3L, "Новый Автор");
        Genre g3 = new Genre(3L, "Новый Жанр");

        Book expectedBook = new Book()
//                .setId(3L)
                .setName("Ночь")
                .setAuthor(a3)
                .setGenre(g3);

        bookService.update(expectedBook);

        Book actualBook = bookService.getById(3L);
        assertEquals(actualBook.getId(), expectedBook.getId());
    }

}
