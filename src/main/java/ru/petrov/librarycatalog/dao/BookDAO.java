package ru.petrov.librarycatalog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.petrov.librarycatalog.models.Book;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book ORDER BY name", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).
                stream().findAny().orElse(null);
    }

    public List<Book> showByPerson(Integer person_id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=? ORDER BY name", new Object[]{person_id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(person_id, name, author, publication_date) VALUES(?, ?, ?, ?)",
                book.getPersonId(),
                book.getName(),
                book.getAuthor(),
                book.getPublicationDate());
    }

    public void update(int id, Book updateBook) {
        jdbcTemplate.update("UPDATE Book set person_id=?, name=?, author=?, publication_date=? WHERE id=?",
                updateBook.getPersonId(),
                updateBook.getName(),
                updateBook.getAuthor(),
                updateBook.getPublicationDate(),
                id);
    }

    public void updateOwner(int id, Integer person_id) {
        jdbcTemplate.update("UPDATE Book set person_id=? WHERE id=?",
                person_id,
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }
}
