package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.dao.BookDaoImpl;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Comment;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    private BookDaoImpl bookDao;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBook(long bookId) {
        Book book = bookDao.getById(bookId);
        return book.getComments();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBook(Book book) {
        return book.getComments();
    }
}
