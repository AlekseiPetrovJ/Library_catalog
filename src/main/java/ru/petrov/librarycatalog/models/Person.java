package ru.petrov.librarycatalog.models;

import javax.validation.constraints.*;

public class Person {
    private int id;
    private static final int MAX_YEAR = 2023;
    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 2, max = 60, message = "Длинна ФИО должна быть 2-60")
    @Pattern(regexp = "([А-ЯЁ][а-яё]+[\\-\\s]?){3,}", message = "ФИО должно быть в формате Фамилия Имя Отчество")
    private String fullname;

    @Min(value = (MAX_YEAR-99), message = "Год рождения не должен быть раньше " + (MAX_YEAR-99))
    @Max(value = MAX_YEAR, message = "Год рождения не должен быть позже " + MAX_YEAR)
    private int yearOfBirth;

    public Person(int id, String fullname, int yearOfBirth) {
        this.id = id;
        this.fullname = fullname;
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
