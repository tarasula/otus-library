package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.AuthorServiceImpl;
import ru.otus.spring.service.BookServiceImpl;
import ru.otus.spring.service.GenreServiceImpl;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands implements Quit.Command {

    private final BookServiceImpl bookService;
    private final AuthorServiceImpl authorService;
    private final GenreServiceImpl genreService;

    @ShellMethod(value = "Hello command", key = {"h", "helo", "hello"})
    public String helloMethod() {
        return "Добро пожаловать в библиотеку, выбирите нужное дейстиве!";
    }

    @ShellMethod(value = "Get All Authors", key = {"a", "author", "authors"})
    public List<Author> getAuthors() {
        return authorService.getAll();
    }

    @ShellMethod(value = "Get Author By Id", key = {"abi"})
    public Author getAuthorById(long id) {
        return authorService.getById(id);
    }

    @ShellMethod(value = "Get All Genres", key = {"g", "genre", "genres"})
    public List<Genre> getGenres() {
        return genreService.getAll();
    }

    @ShellMethod(value = "Get Genre By Id", key = {"gbi"})
    public Genre getGenreById(long id) {
        return genreService.getById(id);
    }

    @ShellMethod(value = "Get All Books", key = {"b", "book", "books"})
    public List<Book> getBooks() {
        return bookService.getAll();
    }

    @ShellMethod(value = "Get Book By Id", key = {"b1"})
    public Book getBookById(long id) {
        return bookService.getById(id);
    }

    @ShellMethod(value = "Create book command", key = {"ib", "cb", "create book", "insert book"})
    public String insert(String name, String authorName, String genreName) {

        if (getAuthorFromDictionary(authorName) == null) {
            authorService.insert(authorName);
        }
        if (getGenreFromDictionary(genreName) == null) {
            genreService.insert(genreName);
        }

        Author author = getAuthorFromDictionary(authorName);
        Genre genre = getGenreFromDictionary(genreName);

        Book book = new Book()
                .setName(name)
                .setAuthor(Long.toString(author.getId()))
                .setGenre(Long.toString(genre.getId()));

        bookService.insert(book);

        return "Your book was created successfully !";
    }

    @ShellMethod(value = "Delete book command", key = {"db", "delete book"})
    public void delete(long id) {
        bookService.delete(id);
    }

    @ShellMethod(value = "Exit the shell.", key = {"quit", "exit"})
    public void quit() {
        System.exit(0);
    }

    private Author getAuthorFromDictionary(String authorName){
        return getAuthors()
                .stream()
                .filter(item -> item.getName().equalsIgnoreCase(authorName))
                .findFirst()
                .orElse(null);
    }

    private Genre getGenreFromDictionary(String genreName){
        return getGenres()
                .stream()
                .filter(item -> item.getName().equalsIgnoreCase(genreName))
                .findFirst()
                .orElse(null);
    }
}
