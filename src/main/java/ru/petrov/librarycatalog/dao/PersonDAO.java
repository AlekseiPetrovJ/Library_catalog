package ru.petrov.librarycatalog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.petrov.librarycatalog.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(String fullname) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE fullname=?", new Object[]{fullname}, new BeanPropertyRowMapper<>(Person.class)).
                stream().findAny();
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).
                stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fullname, year_of_birth) VALUES(?, ?)",
                person.getFullname(),
                person.getYearOfBirth());
    }

    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE Person set fullname=?, year_of_birth=? WHERE id=?",
                updatePerson.getFullname(),
                updatePerson.getYearOfBirth(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
