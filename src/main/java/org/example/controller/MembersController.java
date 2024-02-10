package org.example.controller;

import org.example.dao.MemberDAO;
import org.example.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        List<Member> list = dao.index();
        model.addAttribute("members", list);
        return "members/index";
    }
}
