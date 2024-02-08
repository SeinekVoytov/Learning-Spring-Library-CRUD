package org.example.dao;

import org.example.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        book.setBookId(rs.getInt("book_id"));

        rs.getInt("member_id");
        if (rs.wasNull()) {
            book.setMemberId(-1);
        } else {
            book.setMemberId(rs.getInt("member_id"));
        }

        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setPublishingYear(rs.getInt("publishing_year"));

        return book;
    }
}
