package org.example.controller;

import org.example.dao.MemberDAO;
import org.example.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MembersController {

    private final MemberDAO dao;

    @Autowired
    public MembersController(MemberDAO dao) {
        this.dao = dao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("members", dao.index());
        return "members/index";
    }

    @GetMapping("/{id}")
    public String show(Model model,
                       @PathVariable("id") int id) {

        Optional<Member> optionalMemberToBeShowed = dao.show(id);
        if (optionalMemberToBeShowed.isEmpty()) {
            return "error/not-found";
        }

        Member memberToBeShowed = optionalMemberToBeShowed.get();
        model.addAttribute("member", memberToBeShowed);

        return "members/show";
    }
}
