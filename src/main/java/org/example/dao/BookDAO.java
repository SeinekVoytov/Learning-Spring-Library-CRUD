package org.example.dao;

import org.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>());
    }

    public Optional<Book> show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id = ?",
                new BeanPropertyRowMapper<Book>(), id).stream().findAny();
    }

    public void update(Book updatedBook, int id) {
        jdbcTemplate.update("UPDATE Book SET member_id = ?, title = ?, author = ?, publishing_year = ? WHERE book_id = ?",
                updatedBook.getMemberId(), updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getPublishingYear(), id);
    }

    public void save(Book bookToBeSaved) {
        jdbcTemplate.update("INSERT INTO Book(author, publishing_year, title) VALUES (?, ?, ?)",
                bookToBeSaved.getAuthor(), bookToBeSaved.getPublishingYear(), bookToBeSaved.getTitle());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id = ?", id);
    }
}
