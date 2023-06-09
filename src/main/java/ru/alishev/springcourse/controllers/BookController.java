package ru.alishev.springcourse.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.BookDAO;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequestMapping("/book")
public class BookController {

    private final BookDAO dao;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO dao, PersonDAO personDAO) {
        this.dao = dao;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", dao.index());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        Map<String, Object> object = new HashMap<>();
        object.put("book", dao.show(id));

        Optional<Person> bookOwner = dao.getBookOwner(id);
        if (bookOwner.isPresent()) {
            object.put("owner", bookOwner.get());
        } else {
            object.put("people", personDAO.index());
        }
        model.addAllAttributes(object);

        return "book/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/new";
        dao.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", dao.show(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "book/edit";

        dao.update(id, book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        dao.delete(id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        dao.release(id);
        return ("redirect:/book/" + id);
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id,  @RequestParam("person.id") int selectedPerson) {
        dao.assign(id, selectedPerson);
        return ("redirect:/book/" + id);
    }
}
