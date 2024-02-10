package org.example.dao;

import org.example.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MemberDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Member> index() {
        return jdbcTemplate.query("SELECT * FROM Member", new BeanPropertyRowMapper<>(Member.class));
    }

    public Optional<Member> show(int id) {
        return jdbcTemplate.query("SELECT * FROM Member WHERE member_id = ?",
                new BeanPropertyRowMapper<>(Member.class), id).stream().findAny();
    }

    public void update(Member updatedMember, int id) {

    }

    public void delete(int id) {

    }
}
