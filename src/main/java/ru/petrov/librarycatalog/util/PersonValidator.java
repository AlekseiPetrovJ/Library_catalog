package ru.petrov.librarycatalog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.petrov.librarycatalog.dao.PersonDAO;
import ru.petrov.librarycatalog.models.Person;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Person person = (Person) target;

        if (personDAO.show(person.getFullname()).isPresent() &&
                personDAO.show(person.getFullname()).get().getId()!=person.getId()) {
            errors.rejectValue("fullname", "", "ФИО должно быть уникальным");
        }
    }
}
