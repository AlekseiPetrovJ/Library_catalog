package ru.petrov.librarycatalog.models;

import javax.validation.constraints.*;

public class Book {
    private int id;
    private Integer personId;
    private static final int MAX_YEAR = 2023;
    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min = 2, max = 200, message = "Длинна названия должна быть 2-200")
    private String name;

    @NotEmpty(message = "Автор не должен быть пустым")
    @Size(min = 2, max = 200, message = "Длинна имени автора должна быть 2-200")
    private String author;

    @Min(value = (MAX_YEAR - 99), message = "Год издания не должен быть раньше " + (MAX_YEAR - 99))
    @Max(value = MAX_YEAR, message = "Год издания не должен быть позже " + MAX_YEAR)
    private int publicationDate;

    public Book(int id, Integer personId, String name, String author, int publicationDate) {
        this.id = id;
        this.personId = personId;
        this.name = name;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }
}

