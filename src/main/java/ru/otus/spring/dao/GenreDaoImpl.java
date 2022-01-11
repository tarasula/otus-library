package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcTemplate template;

    @Autowired
    public GenreDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Genre getById(long id) {
        String query = "select * from genre where id = :id";

        return template.queryForObject(query, Map.of("id", id), new GenreMapper());
    }

    @Override
    public List<Genre> getAll() {
        String query = "select * from genre";
        return template.query(query, new GenreMapper());
    }

    @Override
    public void insert(String name) {
        String query = "insert into genre (name) values (:name)";

        var params = Map.of("name", name);
        template.update(query, params);
    }

    @Override
    public void update(Genre genre) {
        String query = "update genre set name = :name where id = :id";

        var params
                = Map.of("name", genre.getName(),
                "id", genre.getId());
        template.update(query, params);
    }

    @Override
    public void delete(long id) {
        String query = "delete from genre where id = :id";
        template.execute(query, Map.of("id", id), PreparedStatement::executeUpdate);
    }

    private static final class GenreMapper implements RowMapper<Genre> {
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Genre()
                    .setId(rs.getLong("id"))
                    .setName(rs.getString("name"));
        }
    }
}
