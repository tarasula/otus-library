package ru.otus.spring.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@DataJpaTest
@Import({AuthorDaoImpl.class, GenreDaoImpl.class, BookDaoImpl.class})
class BookDaoImplTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    BookDaoImpl booksDaoJpa;

    @Test
    void getAllBooksTest() {

        Book expectedBook1 = getExpectedBook(1L, "Власть тьмы");
        expectedBook1.setAuthor(getExpectedAuthor(2L, "Николай Гоголь"));
        expectedBook1.setGenre(getExpectedGenre(2L, "Комедия"));

        Book expectedBook2 = getExpectedBook(2L, "Lets do it");
        expectedBook2.setAuthor(getExpectedAuthor(2L, "Николай Гоголь"));
        expectedBook2.setGenre(getExpectedGenre(2L, "Комедия"));

        List<Book> actualList = booksDaoJpa.getAll();
        assertEquals(actualList, List.of(expectedBook1, expectedBook2));
    }

    @Test
    void getBookByIdTest() {
        Book expectedBook1 = getExpectedBook(1L, "Власть тьмы");
        expectedBook1.setAuthor(getExpectedAuthor(2L, "Николай Гоголь"));
        expectedBook1.setGenre(getExpectedGenre(2L, "Комедия"));

        Book actualBook = booksDaoJpa.getById(1L);
        assertEquals(actualBook, expectedBook1);
    }

    @Test
    void insertBookTest() {
        Author a3 = new Author(3L, "Новый Автор");
        Genre g3 = new Genre(3L, "Новый Жанр");
        Book newBook = new Book()
                .setId(3L)
                .setName("Новая Книга")
                .setAuthor(a3)
                .setGenre(g3);

        Book actualBook = booksDaoJpa.insert(newBook);


        Author expectedAuthor = getExpectedAuthor(3L, "Новый Автор");
        Genre expectedGenre = getExpectedGenre(3L, "Новый Жанр");

        Book expectedBook = getExpectedBook(3L, "Новая Книга");
        expectedBook.setGenre(expectedGenre);
        expectedBook.setAuthor(expectedAuthor);

        assertEquals(actualBook, expectedBook);
    }

    @Test
    void updateBookTest() {

        Book expectedBook = getExpectedBook(1L, "Власть Света");
        expectedBook.setAuthor(getExpectedAuthor(2L, "Николай Гоголь"));
        expectedBook.setGenre(getExpectedGenre(2L, "Комедия"));

        Book book = booksDaoJpa.getById(1L);
        book.setName("Власть Света");
        booksDaoJpa.update(book);

        Book actualBook = booksDaoJpa.getById(1L);
        assertEquals(actualBook, expectedBook);
    }


    @Test
    void deleteBookTest() {
        List<Book> bookListBeforeDelete = booksDaoJpa.getAll();
        assertEquals(bookListBeforeDelete.size(), 2);

        booksDaoJpa.delete(1L);

        List<Book> bookListAfterDelete = booksDaoJpa.getAll();
        assertEquals(bookListAfterDelete.size(), 1);
    }

    private Book getExpectedBook(long id, String bookName) {
        return new Book()
                .setId(id)
                .setName(bookName);
    }

    private Author getExpectedAuthor(long id, String name) {
        return new Author(id, name);
    }

    private Genre getExpectedGenre(long id, String name) {
        return new Genre(id, name);
    }

}