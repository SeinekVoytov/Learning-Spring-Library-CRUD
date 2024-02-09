package org.example.controller;

import org.example.dao.BookDAO;
import org.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO dao;

    @Autowired
    public BooksController(BookDAO dao) {
        this.dao = dao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", dao.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String createNewBook(@ModelAttribute("book") Book newBook) {
        dao.save(newBook);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model,
                           @PathVariable("id") int id) {

        Optional<Book> optionalBookToBeUpdated = dao.show(id);
        if (optionalBookToBeUpdated.isEmpty()) {
            return "error/not-found";
        }

        model.addAttribute("book", optionalBookToBeUpdated.get());

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String saveEditedBook(@ModelAttribute("book") Book updatedBook,
                                 @PathVariable("id") int id) {

        dao.update(updatedBook, id);
        return "redirect:/books";
    }
}
