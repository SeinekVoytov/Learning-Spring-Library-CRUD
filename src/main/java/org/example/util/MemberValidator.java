package org.example.util;

import org.example.dao.MemberDAO;
import org.example.model.Book;
import org.example.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MemberValidator implements Validator {

    private final MemberDAO dao;

    @Autowired
    public MemberValidator(MemberDAO dao) {
        this.dao = dao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Member memberToBeValidated = (Member) target;
        if (memberToBeValidated.getMemberId() == 0 && dao.showByFullName(memberToBeValidated.getFullName()).isPresent() ) {
            errors.rejectValue("fullName", "", "This member is already registered");
        }
    }
}
