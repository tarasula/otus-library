package ru.otus.spring.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.dao.BookDaoImpl;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;
import ru.otus.spring.domain.Genre;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@Import({BookServiceImpl.class, BookDaoImpl.class, CommentServiceImpl.class})
public class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private BookServiceImpl bookService;

    @Test
    void getBookByIdServiceTest() {
        Comment c1 = new Comment(1L, "Comment1");
        Comment c2 = new Comment(2L, "Comment2");
        Comment c3 = new Comment(3L, "Comment3");
        Comment c4 = new Comment(3L, "Comment4");

        Book b = bookService.getById(1L);
        b.setComments(Arrays.asList(c1,c2,c3,c4));
        bookService.update(b);
        List<Comment> commentList = commentService.getCommentsByBook(4L);
        assertThat(commentList).isNotNull();
    }
}
