package ru.otus.spring.service;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getCommentsByBook(long bookId);

    public List<Comment> getCommentsByBook(Book book);
}
