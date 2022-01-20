package ru.otus.spring.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcTemplate template;

    public BookDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void insert(Book book) {
        String query = "insert into books (name, author_id, genre_id) values (:name, :author_id, :genre_id)";

        var params
                = Map.of("name", book.getName(), "author_id", book.getAuthor(), "genre_id", book.getGenre());
        template.update(query, params);
    }

    @Override
    public void update(Book book) {
        String query = "update books set name = :name, author_id = :author_id, genre_id = :genre_id where id = :id";

        var params
                = Map.of("name", book.getName(),
                "author_id", book.getAuthor(),
                "genre_id", book.getGenre(),
                "id", book.getId());
        template.update(query, params);
    }

    @Override
    public void delete(long id) {
        String query = "delete from books where id = :id";
        template.execute(query, Map.of("id", id), PreparedStatement::executeUpdate);
    }

    @Override
    public Book getById(long id) {
        String query = "select b.id, b.name, a.name as author, g.name as genre from BOOKS b inner join AUTHOR a on b.author_id = a.id inner join GENRE g on b.genre_id = g.id where b.id = :id ";
        return template.queryForObject(query, Map.of("id", id), new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        String query = "select b.id, b.name, a.name as author, g.name as genre from BOOKS b inner join AUTHOR a on b.author_id = a.id inner join GENRE g on b.GENRE_ID = g.id";
        return template.query(query, new BookMapper());
    }

    private static final class BookMapper implements RowMapper<Book> {
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book()
                    .setId(rs.getLong("id"))
                    .setAuthor(rs.getString("author"))
                    .setGenre(rs.getString("genre"))
                    .setName(rs.getString("name"));
        }
    }
}
