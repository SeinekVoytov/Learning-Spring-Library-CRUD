package org.example.controller;

import jakarta.validation.Valid;
import org.example.dao.BookDAO;
import org.example.dao.MemberDAO;
import org.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final MemberDAO memberDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, MemberDAO memberDAO) {
        this.bookDAO = bookDAO;
        this.memberDAO = memberDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(Model model,
                       @PathVariable("id") int id) {

        Optional<Book> optionalBookToBeShowed = bookDAO.show(id);
        if (optionalBookToBeShowed.isEmpty()) {
            return "error/not-found";
        }

        Book bookToBeShowed = optionalBookToBeShowed.get();
        model.addAttribute("book", bookToBeShowed);

        if (bookToBeShowed.getMemberId() != -1) {
            model.addAttribute("member", memberDAO.show(bookToBeShowed.getMemberId()).get());
        } else {
            model.addAttribute("members", memberDAO.index());
        }

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String createNewBook(@ModelAttribute("book") @Valid Book newBook,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookDAO.save(newBook);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model,
                           @PathVariable("id") int id) {

        Optional<Book> optionalBookToBeUpdated = bookDAO.show(id);
        if (optionalBookToBeUpdated.isEmpty()) {
            return "error/not-found";
        }

        model.addAttribute("book", optionalBookToBeUpdated.get());

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String saveEditedBook(@ModelAttribute("book") @Valid Book editedBook,
                                 BindingResult bindingResult,
                                 @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return String.format("/books/%d/edit", id);
        }

        bookDAO.update(editedBook, id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/free")
    public String makeBookAvailable(@PathVariable("id") int id) {
        bookDAO.makeBookAvailable(id);
        return String.format("redirect:/books/%d", id);
    }

    @PatchMapping("/{id}/take")
    public String makeBookUnavailable(@ModelAttribute("book") Book book,
                                      @PathVariable("id") int id) {

        bookDAO.makeBookUnavailable(book, id);
        return String.format("redirect:/books/%d", id);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
