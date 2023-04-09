package ru.petrov.librarycatalog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.petrov.librarycatalog.dao.BookDAO;
import ru.petrov.librarycatalog.dao.PersonDAO;
import ru.petrov.librarycatalog.models.Book;
import ru.petrov.librarycatalog.models.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.show(id));
        if (bookDAO.show(id).getPersonId() != null) {
            model.addAttribute("person", personDAO.show(bookDAO.show(id).getPersonId()));
        } else if (!personDAO.index().isEmpty()) {
            model.addAttribute("people", personDAO.index());
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/setowner")
    public String updateSetOwner(@ModelAttribute("book") @Valid Book book,
                                 @ModelAttribute("person") Person person,
                                 @PathVariable("id") int id) {
        bookDAO.updateOwner(id, person.getId());
        return "redirect:/books";
    }

    @PatchMapping("/{id}/clearowner")
    public String updateClearOwner(@ModelAttribute("book") @Valid Book book,
                                   @PathVariable("id") int id) {
        bookDAO.updateOwner(id, null);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
