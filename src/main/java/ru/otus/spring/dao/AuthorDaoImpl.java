package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private NamedParameterJdbcTemplate template;

    @Autowired
    public AuthorDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Author getById(long id) {
        String query = "select * from author where id = :id";
        return template.queryForObject(query, Map.of("id", id), new AuthorMapper());
    }

    @Override
    public List<Author> getAll() {
        String query = "select * from author";
        return template.query(query, new AuthorMapper());
    }

    @Override
    public void insert(String name) {
        String query = "insert into author (name) values (:name)";

        var params = Map.of("name", name);
        template.update(query, params);
    }

    @Override
    public void update(Author author) {
        String query = "update author set name = :name where id = :id";

        var params
                = Map.of("name", author.getName(),
                "id", author.getId());
        template.update(query, params);
    }

    @Override
    public void delete(long id) {
        String query = "delete from author where id = :id";
        template.execute(query, Map.of("id", id), PreparedStatement::executeUpdate);
    }

    private static final class AuthorMapper implements RowMapper<Author> {
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author()
                    .setId(rs.getLong("id"))
                    .setName(rs.getString("name"));
        }
    }
}
