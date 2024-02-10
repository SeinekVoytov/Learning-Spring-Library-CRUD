package org.example.dao;

import org.example.model.Book;
import org.example.model.Member;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberResultSetExtractor implements ResultSetExtractor<List<Member>> {

    @Override
    public List<Member> extractData(ResultSet rs) throws SQLException, DataAccessException {

        Map<Integer, Member> map = new HashMap<>();

        while (rs.next()) {

            int id = rs.getInt("M.member_id");
            Member member = map.get(id);

            if (member == null) {
                member = new Member();
                member.setMemberId(id);
                member.setFullName(rs.getString("full_name"));
                member.setBirthYear(rs.getInt("birth_year"));
                map.put(id, member);
            }

            List<Book> takenBooks = member.getTakenBooks();
            if (takenBooks == null) {
                takenBooks = new ArrayList<>();
                member.setTakenBooks(takenBooks);
            }

            int bookId = rs.getInt("book_id");
            if (rs.wasNull()) {
                continue;
            }

            Book takenBook = new Book();
            takenBook.setBookId(bookId);
            takenBook.setMemberId(id);
            takenBook.setAuthor(rs.getString("author"));
            takenBook.setTitle(rs.getString("title"));
            takenBook.setPublishingYear(rs.getInt("publishing_year"));

            takenBooks.add(takenBook);
        }

        return new ArrayList<>(map.values());
    }
}
